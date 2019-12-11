package com.tuniu.ord.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tuniu.operation.framework.base.json.JsonUtil;
import com.tuniu.operation.framework.base.json.resolver.JsonContext;
import com.tuniu.operation.framework.base.json.resolver.JsonContextImpl;
import com.tuniu.operation.framework.base.json.resolver.JsonPath;
import com.tuniu.operation.framework.base.json.resolver.JsonPathImpl;
import com.tuniu.operation.framework.base.time.DateUtils;
import com.tuniu.ord.common.constant.Constants;
import com.tuniu.ord.common.constant.JsonDataPathUtil;
import com.tuniu.ord.common.exception.OrdCustomException;
import com.tuniu.ord.common.util.BaseDomainUtil;
import com.tuniu.ord.common.util.CollectionUtils;
import com.tuniu.ord.core.datasource.DataSourceSwitch;
import com.tuniu.ord.domain.ManualOrder;
import com.tuniu.ord.domain.ManualOrderOccupy;
import com.tuniu.ord.domain.ManualOrderOccupyNum;
import com.tuniu.ord.domain.ManualReceipt;
import com.tuniu.ord.domain.ManualRemark;
import com.tuniu.ord.domain.ManualRequirement;
import com.tuniu.ord.domain.ManualSupplyment;
import com.tuniu.ord.domain.OrdDealOrder;
import com.tuniu.ord.enums.OrdError;
import com.tuniu.ord.persistence.ManualOrderOccupyMapper;
import com.tuniu.ord.persistence.ManualReceiptMapper;
import com.tuniu.ord.persistence.ManualRemarkMapper;
import com.tuniu.ord.persistence.ManualSupplymentMapper;
import com.tuniu.ord.persistence.OrdDealOrderMapper;
import com.tuniu.ord.proxy.PRDProxy;
import com.tuniu.ord.service.ManualOrderService;
import com.tuniu.ord.service.ManualProductQueryService;
import com.tuniu.ord.service.ProductFilterService;
import com.tuniu.ord.vo.ChangeProductVo;
import com.tuniu.ord.vo.ManualOrderPriceVo;
import com.tuniu.ord.vo.ManualPrdouctResultVo;
import com.tuniu.ord.vo.ManualProductQueryVo;
import com.tuniu.ord.vo.ProductIdParamVo;
import com.tuniu.ord.vo.RequirementVo;
import com.tuniu.ord.vo.SelectedProductVo;
import com.tuniu.ord.vo.common.ListVo;

@Service
public class ManualOrderServiceImpl extends CommonOrderServiceImpl implements ManualOrderService {

    private static Logger logger = LoggerFactory.getLogger(ManualOrderServiceImpl.class);

    @Resource
    protected ManualRemarkMapper remarkMapper;

    @Resource
    private ManualProductQueryService manualProductQueryService;

    @Resource
    private OrdDealOrderMapper dealOrderMapper;

    @Resource
    private PRDProxy prdProxy;

    @Resource
    private ProductFilterService productFilterService;

    @Resource
    private ManualOrderOccupyMapper manualOrderOccupyMapper;

    @Resource
    private ManualSupplymentMapper manualSupplymentMapper;

    @Resource
    private ManualReceiptMapper manualReceiptMapper;

    /**
     * 添加备忘
     * 
     * @param record
     */
    @Override
    public void addRemark(ManualRemark record) {
        if (record == null) {
            throw new IllegalArgumentException("manual remark is null");
        }
        BaseDomainUtil.setBasePropertyValue(record);
        if (record.getId() == null) {
            // new a manual remark record
            remarkMapper.insertSelective(record);
        } else {
            remarkMapper.updateByPrimaryKeySelective(record);
        }
    }

    @Override
    public ListVo querySelectdProduct(RequirementVo vo) throws ParseException {
        ListVo result = new ListVo();
        List<SelectedProductVo> selectedProducts = new LinkedList<SelectedProductVo>();
        ManualRequirement requirement = requirementMapper.selectByManualOrderId(vo.getManualOrderId());
        if (requirement == null) {
            logger.error("order requirement is null");
            // throw new OrdCustomException(OrdError.NO_ORDER_REQUIREMENT);
            logger.warn("no selected product.");
            return result;
        }
        List<ManualOrderOccupy> orderOccupies = occupyMapper.queryInfoByManualOrderId(vo.getManualOrderId());
        List<ManualPrdouctResultVo> allproducts = queryProduct(vo.getMemberId(), requirement);
        if (!CollectionUtils.isNotEmpty(orderOccupies)) {
            if(allproducts == null || !CollectionUtils.isNotEmpty(allproducts)) {
                return result;
            }
            List<ManualPrdouctResultVo> filterProducts = null;
            if(requirement.getdOrderId() == null || requirement.getdOrderId() == 0) {
                // order from orderAccept.html
                filterProducts = productFilterService.filterProductsByNeedNum(allproducts, requirement.getAdultNum() + requirement.getChildNum());
            } else {
                // order from saleBoard.html
                filterProducts = productFilterService.filterProducts(allproducts, requirement);
            }
            for (ManualPrdouctResultVo product : filterProducts) {
                SelectedProductVo selectedProductVo = new SelectedProductVo();
                selectedProductVo.setProductId(requirement.getProductId());
                selectedProductVo.setProductName(requirement.getProductName());
                selectedProductVo.setProductOwnerName(product.getProductOwnerName());
                selectedProductVo.setStartDate(requirement.getStartDate());
                selectedProductVo.setEndDate(DateUtils.stringToDate(product.getBookEndDate()));
                selectedProductVo.setAdultPrice(product.getAdultPrice());
                selectedProductVo.setChildPrice(product.getChildPrice());
                selectedProductVo.setStatus(Constants.MANUAL_ORDER_UNOCCUPY);
                selectedProductVo.setCanSaleNum(product.getCanSaleNum());
                selectedProductVo.setSaledNum(product.getSaledNum());
                selectedProductVo.setOccupiedNum(product.getOccupyNum());
                selectedProductVo.setdOrderId(product.getdOrderId());
                selectedProducts.add(selectedProductVo);
            }
        } else {
            for (ManualOrderOccupy orderOccupy : orderOccupies) {
                OrdDealOrder dOrder = dealOrderMapper.selectByOrderId(orderOccupy.getdOrderId());
                if (dOrder == null) {
                    logger.error("query deal order failure,[DOrderId]:{},[Msg]:{}", orderOccupy.getdOrderId(),
                            " result is empty");
                    throw new OrdCustomException(OrdError.NO_D_ORDER);
                }
                SelectedProductVo selectedProductVo = new SelectedProductVo();
                selectedProductVo.setdOrderId(dOrder.getId());
                selectedProductVo.setProductId(requirement.getProductId());
                selectedProductVo.setProductName(requirement.getProductName());
                selectedProductVo.setProductOwnerName(dOrder.getProductManagerName());
                selectedProductVo.setAdultPrice(dOrder.getAdultPrice());
                selectedProductVo.setChildPrice(dOrder.getChildPrice());
                selectedProductVo.setStartDate(requirement.getStartDate());
                selectedProductVo.setEndDate(requirement.getEndDate());
                selectedProductVo.setStatus(orderOccupy.getStatus());
                selectedProductVo.setCanSaleNum(dOrder.getDealOrderNum() - dOrder.getConfirmNum() - dOrder.getOccupyNum());
                selectedProductVo.setSaledNum(dOrder.getConfirmNum());
                selectedProductVo.setOccupiedNum(dOrder.getOccupyNum());
                selectedProducts.add(selectedProductVo);
            }
            Integer totalNum = requirement.getAdultNum() + requirement.getChildNum();
            Integer occupiedNum = getOccupiedNum(orderOccupies);
            if(totalNum > occupiedNum) {
                Integer needNum = totalNum - occupiedNum;
                if(allproducts != null && CollectionUtils.isNotEmpty(allproducts)) {
                    List<ManualPrdouctResultVo> filterProducts = productFilterService.filterProductsByNeedNum(allproducts, needNum);
                    for (ManualPrdouctResultVo product : filterProducts) {
                        SelectedProductVo selectedProductVo = new SelectedProductVo();
                        selectedProductVo.setProductId(requirement.getProductId());
                        selectedProductVo.setProductName(requirement.getProductName());
                        selectedProductVo.setProductOwnerName(product.getProductOwnerName());
                        selectedProductVo.setStartDate(requirement.getStartDate());
                        selectedProductVo.setEndDate(DateUtils.stringToDate(product.getBookEndDate()));
                        selectedProductVo.setAdultPrice(product.getAdultPrice());
                        selectedProductVo.setChildPrice(product.getChildPrice());
                        selectedProductVo.setStatus(Constants.MANUAL_ORDER_UNOCCUPY);
                        selectedProductVo.setCanSaleNum(product.getCanSaleNum());
                        selectedProductVo.setSaledNum(product.getSaledNum());
                        selectedProductVo.setOccupiedNum(product.getOccupyNum());
                        selectedProductVo.setdOrderId(product.getdOrderId());
                        selectedProducts.add(selectedProductVo);
                    }
                }
            }
        }
        result.setRows(selectedProducts);
        result.setCount(selectedProducts.size());
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void occupy(RequirementVo vo) {
        ManualRequirement requirement = getRequirement(vo.getManualOrderId());
        if (!requirement.getId().equals(vo.getRequirementId())) {
            logger.error("requirementId is not correct.");
            throw new OrdCustomException(OrdError.OCCUPY_ERROR);
        }
        int needOccupyNum = requirement.getAdultNum() + requirement.getChildNum() - getOccupiedNum(vo.getManualOrderId());
        if (needOccupyNum > 0) {
            ManualProductQueryVo manualProductQueryVo = new ManualProductQueryVo();
            manualProductQueryVo.setMemberId(vo.getMemberId());
            manualProductQueryVo.setProductId(requirement.getProductId());
            manualProductQueryVo.setProductName(requirement.getProductName());
            manualProductQueryVo.setDepartDate(DateUtils.dateToString(requirement.getStartDate()));
            manualProductQueryVo.setDestCategoryId(requirement.getDestCategoryId());
            manualProductQueryVo.setFirstDestGroupId(requirement.getFirstDestGroupId());
            manualProductQueryVo.setSecDestGroupId(requirement.getSecDestGroupId());
            manualProductQueryVo.setStart(0);
            manualProductQueryVo.setLimit(1000);
            ListVo listVo = manualProductQueryService.queryManualProduct(manualProductQueryVo);

            if (needOccupyNum <= getCanSaleNum(listVo.getRows())) {

                // 保存销售单的成人数，儿童数和总人数。后面依据这个人数进行分配
                OrderTourists orderTourists = new OrderTourists(requirement.getAdultNum(), requirement.getChildNum());

                doOccupy(requirement, listVo.getRows(), orderTourists, true);
            }
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void newOccupy(RequirementVo vo) {
        ManualRequirement requirement = getRequirement(vo.getManualOrderId());
        if (!requirement.getId().equals(vo.getRequirementId())) {
            logger.error("requirementId is not correct.");
            throw new OrdCustomException(OrdError.OCCUPY_ERROR);
        }
        Map<Integer, Integer> occupiedMap = getOccupiedAdultChildNum(vo.getManualOrderId());
        int occupiedAdultNum = occupiedMap.get(Constants.ADULT);
        int occupiedChildNum = occupiedMap.get(Constants.CHILD);
        int needOccupyAdultNum = requirement.getAdultNum() - occupiedAdultNum;
        int needOccupyChildNum = requirement.getChildNum() - occupiedChildNum;
        int needOccupyNum = needOccupyAdultNum +  needOccupyChildNum;

        if (needOccupyNum > 0) {
            List<ManualPrdouctResultVo> allProducts = queryProduct(vo.getMemberId(), requirement);

            ManualPrdouctResultVo filterProduct = productFilterService.filterProducts(allProducts, vo.getdOrderId());
            if(filterProduct == null) {
                logger.error("manual order occupy failure.[ManualOrderId]:{},[Msg]:{}", vo.getManualOrderId(), " can not filter product.");
                throw new OrdCustomException(OrdError.OCCUPY_ERROR);
            }
            // 保存销售单的成人数，儿童数和总人数。后面依据这个人数进行分配
            OrderTourists orderTourists = new OrderTourists(needOccupyAdultNum, needOccupyChildNum);
            doOccupy(requirement, filterProduct, orderTourists);
        } else {
            logger.warn("order have already bean occupied.[ManualOrderId]:{}", vo.getManualOrderId());
            throw new OrdCustomException(OrdError.NO_NEED_OCCUPY);
        }
    }

    private void doOccupy(ManualRequirement requirement, ManualPrdouctResultVo product, OrderTourists orderTourists) {
        Integer canSaleNum = product.getCanSaleNum();
        if (canSaleNum <= 0) {
            logger.error("manual order occupy failure.[ManualOrderId]:{},[DOrderId]:{},[Msg]:{}", 
                    requirement.getManualOrderId(), 
                    product.getdOrderId(),
                    "dOrder's left number not enough.");
            throw new OrdCustomException(OrdError.OCCUPY_ERROR);
        }
        if (canSaleNum >= orderTourists.getTotalNum()) {
            saveOrderOccupy(requirement.getManualOrderId(), product.getdOrderId(), orderTourists.getAdultNum(),
                    orderTourists.getChildNum(), Constants.MANUAL_ORDER_OCCUPY_SUCCESS);
            updateDealOrder(product.getdOrderId(), orderTourists.getTotalNum());
            canSaleNum -= orderTourists.getTotalNum();
            orderTourists.clear();
        } else {
            if (canSaleNum > 0 && orderTourists.getAdultNum() > 0) {
                if (canSaleNum >= orderTourists.getAdultNum()) {
                    int assignChildNum = canSaleNum - orderTourists.getAdultNum();
                    saveOrderOccupy(requirement.getManualOrderId(), product.getdOrderId(), orderTourists.getAdultNum(),
                            assignChildNum, Constants.MANUAL_ORDER_OCCUPY_SUCCESS);
                    updateDealOrder(product.getdOrderId(), orderTourists.getAdultNum());
                    canSaleNum -= orderTourists.getAdultNum();
                    canSaleNum -= assignChildNum;
                    orderTourists.assignedAdult(orderTourists.getAdultNum());
                    orderTourists.assignedChild(assignChildNum);
                } else {
                    saveOrderOccupy(requirement.getManualOrderId(), product.getdOrderId(), canSaleNum, 0,
                            Constants.MANUAL_ORDER_OCCUPY_SUCCESS);
                    updateDealOrder(product.getdOrderId(), canSaleNum);
                    orderTourists.assignedAdult(canSaleNum);
                    canSaleNum = 0;
                }
            }
            if (canSaleNum > 0 && orderTourists.getChildNum() > 0) {
                if (canSaleNum >= orderTourists.getChildNum()) {
                    saveOrderOccupy(requirement.getManualOrderId(), product.getdOrderId(), 0, orderTourists.getChildNum(),
                            Constants.MANUAL_ORDER_OCCUPY_SUCCESS);
                    updateDealOrder(product.getdOrderId(), orderTourists.getChildNum());
                    canSaleNum -= orderTourists.getChildNum();
                    orderTourists.assignedChild(orderTourists.getChildNum());
                } else {
                    saveOrderOccupy(requirement.getManualOrderId(), product.getdOrderId(), 0, canSaleNum,
                            Constants.MANUAL_ORDER_OCCUPY_SUCCESS);
                    updateDealOrder(product.getdOrderId(), canSaleNum);
                    orderTourists.assignedChild(canSaleNum);
                    canSaleNum = 0;
                }
            }
        }
        if (!orderTourists.isAssignedComplite()) {
//            saveOrderOccupy(requirement.getManualOrderId(), null, orderTourists.getAdultNum(), orderTourists.getChildNum(),
//                    Constants.MANUAL_ORDER_OCCUPY_FAILURE);
//            orderTourists.clear();
            logger.error("manual order occupy number not enough.[ManualOrderId]:{}", requirement.getManualOrderId());
        }
    }
    
    private void doOccupy(ManualRequirement requirement, List<? extends Object> rows, OrderTourists orderTourists,
            boolean greedy) {
        for (Object object : rows) {
            // 当人员已经分配完成，则退出循环
            if (orderTourists.isAssignedComplite()) {
                logger.info("order tourist assign complete, [ManualOrderId]:{}", requirement.getManualOrderId());
                break;
            }

            ManualPrdouctResultVo product = (ManualPrdouctResultVo) object;

            if (product.getCanSaleNum() <= 0) {
                continue;
            }
            if (product.getCanSaleNum() >= orderTourists.getTotalNum()) {
                saveOrderOccupy(requirement.getManualOrderId(), product.getdOrderId(), orderTourists.getAdultNum(),
                        orderTourists.getChildNum(), Constants.MANUAL_ORDER_OCCUPY_SUCCESS);
                updateDealOrder(product.getdOrderId(), orderTourists.getTotalNum());
                orderTourists.clear();
            } else {
                if (orderTourists.getAdultNum() > 0) {
                    if (product.getCanSaleNum() >= orderTourists.getAdultNum()) {
                        int assignChildNum = product.getCanSaleNum() - orderTourists.getAdultNum();
                        saveOrderOccupy(requirement.getManualOrderId(), product.getdOrderId(), orderTourists.getAdultNum(),
                                assignChildNum, Constants.MANUAL_ORDER_OCCUPY_SUCCESS);
                        updateDealOrder(product.getdOrderId(), orderTourists.getAdultNum());
                        orderTourists.assignedAdult(orderTourists.getAdultNum());
                        orderTourists.assignedChild(assignChildNum);
                    } else {
                        saveOrderOccupy(requirement.getManualOrderId(), product.getdOrderId(), product.getCanSaleNum(), 0,
                                Constants.MANUAL_ORDER_OCCUPY_SUCCESS);
                        updateDealOrder(product.getdOrderId(), product.getCanSaleNum());
                        orderTourists.assignedAdult(product.getCanSaleNum());
                    }
                }
                if (orderTourists.getChildNum() > 0) {
                    if (product.getCanSaleNum() >= orderTourists.getChildNum()) {
                        saveOrderOccupy(requirement.getManualOrderId(), product.getdOrderId(), 0, orderTourists.getChildNum(),
                                Constants.MANUAL_ORDER_OCCUPY_SUCCESS);
                        updateDealOrder(product.getdOrderId(), orderTourists.getChildNum());
                        orderTourists.assignedChild(orderTourists.getChildNum());
                    } else {
                        saveOrderOccupy(requirement.getManualOrderId(), product.getdOrderId(), 0, product.getCanSaleNum(),
                                Constants.MANUAL_ORDER_OCCUPY_SUCCESS);
                        updateDealOrder(product.getdOrderId(), product.getCanSaleNum());
                        orderTourists.assignedChild(product.getCanSaleNum());
                    }
                }
            }
        }
        if (!orderTourists.isAssignedComplite() && greedy) {
            saveOrderOccupy(requirement.getManualOrderId(), null, orderTourists.getAdultNum(), orderTourists.getChildNum(),
                    Constants.MANUAL_ORDER_OCCUPY_FAILURE);
            orderTourists.clear();
        }
    }

    private int getCanSaleNum(List<? extends Object> rows) {
        if (!CollectionUtils.isNotEmpty(rows)) {
            return 0;
        }
        int totalCanSaleNum = 0;
        for (Object row : rows) {
            totalCanSaleNum += ((ManualPrdouctResultVo) row).getCanSaleNum();
        }
        return totalCanSaleNum;
    }

    private void saveOrderOccupy(Integer manualOrderId, Integer dOrderId, Integer adultNum, Integer childNum, Integer status) {
        ManualOrderOccupy orderOccupy = new ManualOrderOccupy();
        orderOccupy.setManualOrderId(manualOrderId);
        orderOccupy.setdOrderId(dOrderId);
        orderOccupy.setAdultNum(adultNum);
        orderOccupy.setChildNum(childNum);
        orderOccupy.setNumber(adultNum + childNum);
        orderOccupy.setStatus(status);
        BaseDomainUtil.setBasePropertyValue(orderOccupy);
        occupyMapper.insertSelective(orderOccupy);
    }

    private void updateDealOrder(Integer dOrderId, Integer occupyNum) {
        OrdDealOrder dOrder = dealOrderMapper.selectByPrimaryKey(dOrderId);
        if (dOrder == null) {
            logger.error("query d order failure,[DOrderId]:{}", dOrderId);
        }
        dOrder.setOccupyNum(dOrder.getOccupyNum() + occupyNum);
        dealOrderMapper.updateByPrimaryKeySelective(dOrder);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void reoccupy(RequirementVo vo) {
        newOccupy(vo);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void cancelOccupy(Integer manualOrderId, Integer requirementId, Integer dOrderId) {
        ManualOrder manualOrder = orderMapper.selectByPrimaryKey(manualOrderId);
        if (manualOrder == null) {
            logger.error("manual order id is not correct.");
            throw new OrdCustomException(OrdError.CANCEL_OCCUPY_ERROR);
        }
        ManualRequirement requirement = getRequirement(manualOrderId);
        if (requirement == null || !requirement.getId().equals(requirementId)) {
            logger.error("requirementId is not correct.");
            throw new OrdCustomException(OrdError.CANCEL_OCCUPY_ERROR);
        }
        OrdDealOrder dOrder = dealOrderMapper.selectByPrimaryKey(dOrderId);
        if (dOrder == null) {
            logger.error("d order id is not correct.");
            throw new OrdCustomException(OrdError.CANCEL_OCCUPY_ERROR);
        }
        List<ManualOrderOccupy> orderOccupies = occupyMapper.queryInfoByManualOrderId(manualOrderId);
        if (!CollectionUtils.isNotEmpty(orderOccupies)) {
            logger.warn("manual order occupy record is empty. maybe have bean already canceled.");
            // throw new OrdCustomException(OrdError.CANCEL_OCCUPY_ERROR);
            return;
        }
        for (ManualOrderOccupy orderOccupy : orderOccupies) {
            if (orderOccupy.getdOrderId().equals(dOrder.getId())
                    && Constants.MANUAL_ORDER_OCCUPY_SUCCESS.equals(orderOccupy.getStatus())) {
                orderOccupy.setDelFlag(Constants.DELETE);
                BaseDomainUtil.setUpdatePropertyValue(orderOccupy);
                occupyMapper.updateByPrimaryKeySelective(orderOccupy);
                dOrder.setOccupyNum(dOrder.getOccupyNum() - orderOccupy.getNumber());
                BaseDomainUtil.setUpdatePropertyValue(dOrder);
                dealOrderMapper.updateByPrimaryKeySelective(dOrder);
            }
        }
        /*
         * if(allOccupyCanceled(orderOccupies)) { requirement.setDelFlag(Constants.DELETE);
         * BaseDomainUtil.setUpdatePropertyValue(requirement); requirementMapper.updateByPrimaryKeySelective(requirement); }
         */
    }

    private boolean allOccupyCanceled(List<ManualOrderOccupy> orderOccupies) {
        if (!CollectionUtils.isNotEmpty(orderOccupies)) {
            return true;
        }
        boolean allDelFlag = true;
        for (ManualOrderOccupy orderOccupy : orderOccupies) {
            if (!Constants.DELETE.equals(orderOccupy.getDelFlag())) {
                allDelFlag = false;
            }
        }
        return allDelFlag;
    }

    @Override
    public void changeProduct(ChangeProductVo vo) {
        ManualOrder manualOrder = orderMapper.selectByPrimaryKey(vo.getManualOrderId());
        if (manualOrder == null) {
            logger.error("change product failure,[ManualOrderId]:{},[Msg]:{}", vo.getManualOrderId(),
                    "manual order is not exist.");
            throw new OrdCustomException(OrdError.NO_MANUAL_ORDER);
        }
        if (getOccupiedNum(vo.getManualOrderId()) > 0) {
            logger.error("original product already have been occupied, please cancel occupy first.[ManualOrderId]:{}",
                    vo.getManualOrderId());
            throw new OrdCustomException(OrdError.ORIGINAL_PRODUCT_BEEN_OCCUPIED);
        }
        ManualRequirement requirement = getRequirement(vo.getManualOrderId());
        if (requirement == null) {
            logger.error("change product failure,[ManualOrderId]:{},[Msg]:{}", vo.getManualOrderId(),
                    "manual requirement is not exist.");
            throw new OrdCustomException(OrdError.NO_ORDER_REQUIREMENT);
        }
        if(!requirement.getProductId().equals(vo.getProductId())) {
            requirement.setStartDate(vo.getDepartDate());
            requirement.setdOrderId(vo.getdOrderId());
            //更换了产品
            ProductIdParamVo productVo = new ProductIdParamVo();
            productVo.setProductId(vo.getProductId());
            String response = prdProxy.getProductInfo(productVo);
            if (response == null) {
                logger.error("query product failure.[ProductId]:{},[Msg]:{}", vo.getProductId(), "response:" + response);
                throw new OrdCustomException(OrdError.QUERY_PRODUCT_ERROR);
            }
            JsonPath jsonPath = new JsonPathImpl();
            JsonContext context = new JsonContextImpl(response);
            if (jsonPath.getBooleanValue(context, JsonDataPathUtil.SUCCESS_FLAG)) {
                requirement.setDestCategoryId(jsonPath.getIntValue(context, JsonDataPathUtil.PRODUCT_BASE_DESTCATEGORYID));
                requirement.setDestCategoryName(jsonPath.getStringValue(context, JsonDataPathUtil.PRODUCT_BASE_DESTCATEGORYNAME));
                requirement.setFirstDestGroupId(jsonPath.getIntValue(context, JsonDataPathUtil.PRODUCT_BASE_FIRSTDESTGROUPID));
                requirement.setFirstDestGroupName(jsonPath.getStringValue(context, JsonDataPathUtil.PRODUCT_BASE_FIRSTDESTGROUPNAME));
                requirement.setSecDestGroupId(jsonPath.getIntValue(context, JsonDataPathUtil.PRODUCT_BASE_SECDESTGROUPID));
                requirement.setSecDestGroupName(jsonPath.getStringValue(context, JsonDataPathUtil.PRODUCT_BASE_SECDESTGROUPNAME));
                requirement.setDestId(jsonPath.getIntValue(context, JsonDataPathUtil.PRODUCT_BASE_DESTID));
                requirement.setDestName(jsonPath.getStringValue(context, JsonDataPathUtil.PRODUCT_BASE_DESTNAME));
                requirement.setProductId(vo.getProductId());
                requirement.setProductName(jsonPath.getStringValue(context, JsonDataPathUtil.PRODUCT_BASE_PRODUCTNAME));
                requirement.setdOrderId(vo.getdOrderId());
                requirementMapper.updateByPrimaryKeySelective(requirement);
            } else {
                logger.error("query product failure.[ProductId]:{}", vo.getProductId());
                throw new OrdCustomException(OrdError.QUERY_PRODUCT_ERROR);
            }
        } else if (!DateUtils.isSameDay(requirement.getStartDate(), vo.getDepartDate())) {
            // 更换了团期
            requirement.setStartDate(vo.getDepartDate());
            requirement.setdOrderId(vo.getdOrderId());
            requirementMapper.updateByPrimaryKeySelective(requirement);
        } else if (!requirement.getdOrderId().equals(vo.getdOrderId())) {
            // 更换了批次
            requirement.setdOrderId(vo.getdOrderId());
            requirementMapper.updateByPrimaryKeySelective(requirement);
        }
    }
    
    private List<ManualPrdouctResultVo> queryProduct (Integer memberId, ManualRequirement requirement){
        ManualProductQueryVo manualProductQueryVo = new ManualProductQueryVo();
        manualProductQueryVo.setMemberId(memberId);
        manualProductQueryVo.setProductId(requirement.getProductId());
        manualProductQueryVo.setProductName(requirement.getProductName());
        manualProductQueryVo.setDepartDate(DateUtils.dateToString(requirement.getStartDate()));
        manualProductQueryVo.setDestCategoryId(requirement.getDestCategoryId());
        manualProductQueryVo.setFirstDestGroupId(requirement.getFirstDestGroupId());
        manualProductQueryVo.setSecDestGroupId(requirement.getSecDestGroupId());
        manualProductQueryVo.setTenantId(DataSourceSwitch.getTenantId());
        manualProductQueryVo.setOnlyCanOrderPrd(true);
        manualProductQueryVo.setStart(0);
        manualProductQueryVo.setLimit(1000);
        System.out.println(JsonUtil.toString(manualProductQueryVo));
        ListVo listVo = manualProductQueryService.queryManualProduct(manualProductQueryVo);
        if(listVo == null || CollectionUtils.isEmpty(listVo.getRows())) {
            return new LinkedList<ManualPrdouctResultVo>();
        } else {
            return (List<ManualPrdouctResultVo>)listVo.getRows();
        }
    } 

    class OrderTourists {
        private Integer adultNum;
        private Integer childNum;
        private Integer totalNum;

        public OrderTourists(Integer adultNum, Integer childNum) {
            this.adultNum = adultNum;
            this.childNum = childNum;
            this.totalNum = adultNum + childNum;
        }

        /**
         * @return the adultNum
         */
        public Integer getAdultNum() {
            return adultNum;
        }

        /**
         * @param adultNum
         *            the adultNum to set
         */
        public void setAdultNum(Integer adultNum) {
            this.adultNum = adultNum;
            reCalcTotal();
        }

        /**
         * @return the childNum
         */
        public Integer getChildNum() {
            return childNum;
        }

        /**
         * @param childNum
         *            the childNum to set
         */
        public void setChildNum(Integer childNum) {
            this.childNum = childNum;
            reCalcTotal();
        }

        /**
         * @return the totalNum
         */
        public Integer getTotalNum() {
            return totalNum;
        }

        public boolean isAssignedComplite() {
            if (totalNum <= 0 && adultNum <= 0 && childNum <= 0)
                return true;
            return false;
        }

        public boolean assignedAdult(Integer adultNum) {
            // if (this.adultNum < adultNum)
            // return false;
            this.adultNum -= adultNum;
            reCalcTotal();
            return true;
        }

        public boolean assignedChild(Integer childNum) {
            // if (this.childNum < childNum)
            // return false;
            this.childNum -= childNum;
            reCalcTotal();
            return true;
        }

        protected void clear() {
            this.adultNum = 0;
            this.childNum = 0;
            this.totalNum = 0;
        }

        private void reCalcTotal() {
            this.totalNum = this.adultNum + this.childNum;
        }
    }

    @Override
    public ManualOrderPriceVo getOrderPrice(Integer manualOrderId) {
        ManualOrderPriceVo manualOrderPriceVo = new ManualOrderPriceVo();

        BigDecimal amount = BigDecimal.ZERO;

        List<ManualOrderOccupyNum> queryOccupyPriceList = manualOrderOccupyMapper.queryOccupyPriceList(manualOrderId);
        if (CollectionUtils.isNotEmpty(queryOccupyPriceList)) {
            for (ManualOrderOccupyNum occupyNum : queryOccupyPriceList) {
                BigDecimal adultTotal = occupyNum.getAdultPrice().multiply(new BigDecimal(occupyNum.getAdultNum()));
                BigDecimal childTotal = occupyNum.getChildPrice().multiply(new BigDecimal(occupyNum.getChildNum()));
                amount = amount.add(childTotal).add(adultTotal);
            }
        }

        List<ManualSupplyment> supplymentList = manualSupplymentMapper.queryByManualOrderId(manualOrderId);
        if (CollectionUtils.isNotEmpty(supplymentList)) {
            for (ManualSupplyment supplyment : supplymentList) {
                BigDecimal totalCost = supplyment.getPrice().multiply(new BigDecimal(supplyment.getNumber()));
                amount = amount.add(totalCost);
            }
        }
        manualOrderPriceVo.setOrderPrice(amount);
        
        BigDecimal leftAmount = amount;

        List<ManualReceipt> manualReceipts = manualReceiptMapper.queryManualReceiptByManualOrderId(manualOrderId);
        if (CollectionUtils.isNotEmpty(manualReceipts)) {
            for (ManualReceipt manualReceipt : manualReceipts) {
                leftAmount = leftAmount.subtract(manualReceipt.getReceiptPrice());
            }
        }
        manualOrderPriceVo.setReceivablesMoney(leftAmount);

        return manualOrderPriceVo;
    }
}
