package com.tuniu.ord.common.task;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tuniu.operation.framework.base.time.DateUtils;
import com.tuniu.ord.common.exception.OrdCustomException;
import com.tuniu.ord.common.util.BaseDomainUtil;
import com.tuniu.ord.domain.ManualOrder;
import com.tuniu.ord.domain.ManualTask;
import com.tuniu.ord.domain.ManualTaskFlow;
import com.tuniu.ord.enums.OrdError;
import com.tuniu.ord.persistence.ManualOrderMapper;
import com.tuniu.ord.persistence.ManualTaskFlowMapper;
import com.tuniu.ord.persistence.ManualTaskMapper;

@Service
public class TaskFlow {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(TaskFlow.class);
    
    @Resource
    private ManualTaskMapper taskMapper;
    
    @Resource
    private ManualTaskFlowMapper taskFlowMapper;
    
    @Resource
    private ManualOrderMapper orderMapper;

    /**
     * 初始化任务流
     * @param orderId
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void initTaskFlow(Integer manualOrderId) {
        Map<ManualTaskWapper, ManualTaskFlowWapper> taskFlows = new LinkedHashMap<ManualTaskWapper, ManualTaskFlowWapper>();
        for (OrderTaskEnum taskEnum : OrderTaskEnum.values()) {
            ManualTaskWapper task = mappingTask(manualOrderId, taskEnum);
            ManualTaskFlowWapper taskFlow = mappingTaskFlow(task);
            taskFlows.put(task, taskFlow);
        }
        
        for(Iterator<Entry<ManualTaskWapper, ManualTaskFlowWapper>> it = taskFlows.entrySet().iterator();it.hasNext();){
            Entry<ManualTaskWapper, ManualTaskFlowWapper> entry = (Entry<ManualTaskWapper, ManualTaskFlowWapper>)it.next();  
            if(entry.getValue() != null){
                OrderTaskEnum preTaskEnum = entry.getKey().getPreTask();
                if (preTaskEnum != null) {
                    Set<ManualTaskWapper> taskSet = taskFlows.keySet();
                    Iterator<ManualTaskWapper> taskIt = taskSet.iterator();
                    while (taskIt.hasNext()) {
                        ManualTaskWapper task = taskIt.next();
                        if (task.getTaskEnum().getKey().equals(preTaskEnum.getKey())) {
                            entry.getValue().setPreTaskId(task.getTaskId());
                            break;
                        }
                    }
                }
                OrderTaskEnum suffixTaskEnum = entry.getKey().getSuffixTask();
                if(suffixTaskEnum != null) {
                    Set<ManualTaskWapper> taskSet = taskFlows.keySet();
                    Iterator<ManualTaskWapper> taskIt = taskSet.iterator();
                    while (taskIt.hasNext()) {
                        ManualTaskWapper task = taskIt.next();
                        if (task.getTaskEnum().getKey().equals(suffixTaskEnum.getKey())) {
                            entry.getValue().setPostTaskId(task.getTaskId());
                            break;
                        }
                    }
                }
            }
            taskFlowMapper.updateByPrimaryKeySelective(entry.getValue().getTaskFlow());
        }
        // starting first task node
        startFirstTaskNode(taskFlows);
    }

    private void startFirstTaskNode(Map<ManualTaskWapper, ManualTaskFlowWapper> taskFlows) {
        ManualTaskWapper orderTaskNode = getTaskNode(taskFlows, OrderTaskEnum.ORDER);
        taskFlows.get(orderTaskNode).setStatus(TaskStatusEnum.ENABLE);
        taskFlows.get(orderTaskNode).setStartTime(DateUtils.now());
        taskFlowMapper.updateByPrimaryKeySelective(taskFlows.get(orderTaskNode).getTaskFlow());
    }

    private ManualTaskFlowWapper createTaskFlow(ManualTaskWapper task) {
        ManualTaskFlowWapper taskFlowWapper = new ManualTaskFlowWapper();
        ManualTaskFlow taskFlow = new ManualTaskFlow();
        taskFlow.setTaskId(task.getTask().getId());
        taskFlow.setTaskStatus(TaskStatusEnum.DISABLE.getKey());
        BaseDomainUtil.setBasePropertyValue(taskFlow);
        taskFlowMapper.insertSelective(taskFlow);
        taskFlowWapper.setTaskFlow(taskFlow);
        taskFlowWapper.setStatus(TaskStatusEnum.DISABLE);
        return taskFlowWapper;
    }

    private ManualTaskWapper createTask(Integer manualOrderId, OrderTaskEnum taskEnum) {
        ManualTaskWapper taskWapper = new ManualTaskWapper();
        ManualTask task = new ManualTask();
        task.setManualOrderId(manualOrderId);
        task.setTaskCode(taskEnum.getKey());
        task.setTaskName(taskEnum.getName());
        task.setUrl(taskEnum.getUrl());
        BaseDomainUtil.setBasePropertyValue(task);
        taskMapper.insertSelective(task);
        taskWapper.setTask(task);
        taskWapper.setTaskEnum(taskEnum);
        return taskWapper;
    } 
    
    /**
     * 流程向前走一步
     */
    @Deprecated
    public void forward(Integer orderId){
        Map<ManualTaskWapper, ManualTaskFlowWapper> taskFlows = fetchTaskFlow(orderId);
        ManualTaskWapper firstTask = getTaskNode(taskFlows, OrderTaskEnum.ORDER);
        if (!taskFlows.get(firstTask).getStatus().equals(TaskStatusEnum.FINISH.getKey())) {
            
        }
    }
    
    @Transactional(propagation = Propagation.REQUIRED)
    public void finish(Integer manualOrderId, OrderTaskEnum taskEnum) {
        Map<ManualTaskWapper, ManualTaskFlowWapper> taskFlows = fetchTaskFlow(manualOrderId);
        doFinish(taskFlows, taskEnum);
        updateManualOrder(manualOrderId, OrderTaskEnum.getNextTask(taskEnum));
    }

    private void updateManualOrder(Integer manualOrderId, OrderTaskEnum nextTask) {
        ManualOrder manualOrder = orderMapper.selectByPrimaryKey(manualOrderId);
        manualOrder.setStatusCode(nextTask.getKey());
        orderMapper.updateByPrimaryKeySelective(manualOrder);
    }

    private void doFinish(Map<ManualTaskWapper, ManualTaskFlowWapper> taskFlows, OrderTaskEnum taskEnum) {
        ManualTaskWapper taskWapper = getTaskNode(taskFlows, taskEnum);
        ManualTaskFlowWapper taskFlowWapper = taskFlows.get(taskWapper);
        if (taskFlowWapper.getStatus().equals(TaskStatusEnum.DISABLE)) {
            LOGGER.info("task node is disable,[orderId]:{},[task]:{},[flow]:{}",
                    taskWapper.getManualOrderId(),taskEnum.getName(),
                    taskFlowWapper.getStatus().getName());
            throw new OrdCustomException(OrdError.FINISH_TASK_ERROR);
        } else if (taskFlowWapper.getStatus().equals(TaskStatusEnum.FINISH)) {
            LOGGER.info("task node is already finished,[orderId]:{},[task]:{},[flow]:{}",
                    taskWapper.getManualOrderId(),taskEnum.getName(),
                    taskFlowWapper.getStatus().getName());
            return ;
        } else if (taskFlowWapper.getStatus().equals(TaskStatusEnum.ENABLE)) {
            taskFlowWapper.setEndTime(DateUtils.now());
            taskFlowWapper.setStatus(TaskStatusEnum.FINISH);
            taskFlowMapper.updateByPrimaryKeySelective(taskFlowWapper.getTaskFlow());
            Integer postTaskId = taskFlowWapper.getPostTaskId();
            if (postTaskId == null) {
                LOGGER.info("all task have bean finished,[orderId]:{}", taskWapper.getManualOrderId());
                return ;
            }
            ManualTaskWapper postTaskWapper = getTaskByTaskId(taskFlows, taskFlowWapper.getPostTaskId());
            ManualTaskFlowWapper postTaskFlowWapper = taskFlows.get(postTaskWapper);
            postTaskFlowWapper.setStartTime(DateUtils.now());
            postTaskFlowWapper.setStatus(TaskStatusEnum.ENABLE);
            taskFlowMapper.updateByPrimaryKeySelective(postTaskFlowWapper.getTaskFlow());
        } else {
            LOGGER.info("task node in unknow status,[orderId]:{},[task]:{},[flow]:{}",
                    taskWapper.getManualOrderId(),taskEnum.getName(),
                    taskFlowWapper.getStatus().getName());
            throw new OrdCustomException(OrdError.UNKNOW_TASK_ERROR);
        }
    }
    
    private ManualTaskWapper getTaskByTaskId(Map<ManualTaskWapper, ManualTaskFlowWapper> taskFlows, Integer taskId) {
        Iterator<ManualTaskWapper> it = taskFlows.keySet().iterator();
        while (it.hasNext()) {
            ManualTaskWapper taskWapper = it.next();
            if (taskWapper.getTaskId().equals(taskId)) {
                return taskWapper;
            }
        }
        return null;
    }

    private Map<ManualTaskWapper, ManualTaskFlowWapper> fetchTaskFlow(Integer manualOrderId){
        Map<ManualTaskWapper, ManualTaskFlowWapper> taskFlows = new LinkedHashMap<ManualTaskWapper, ManualTaskFlowWapper>();
        for (OrderTaskEnum taskEnum : OrderTaskEnum.values()) {
            ManualTaskWapper task = mappingTask(manualOrderId, taskEnum);
            ManualTaskFlowWapper taskFlow = mappingTaskFlow(task);
            taskFlows.put(task, taskFlow);
        }
        return taskFlows;
    } 

    private ManualTaskWapper getTaskNode(Map<ManualTaskWapper, ManualTaskFlowWapper> taskFlows, OrderTaskEnum taskEnum) {
        Set<ManualTaskWapper> taskSet = taskFlows.keySet();
        Iterator<ManualTaskWapper> taskIt = taskSet.iterator();
        while (taskIt.hasNext()) {
            ManualTaskWapper task = taskIt.next();
            if (task.getTaskEnum().getKey().equals(taskEnum.getKey())) {
                return task;
            }
        }
        throw new OrdCustomException(OrdError.NO_SUCH_TASK);
    }

    private ManualTaskFlowWapper mappingTaskFlow(ManualTaskWapper task) {
        ManualTaskFlowWapper taskFlowWapper = new ManualTaskFlowWapper();
        ManualTaskFlow taskFlow = taskFlowMapper.selectByTaskId(task.getTaskId());
        if (taskFlow == null) {
            taskFlowWapper = createTaskFlow(task);
            return taskFlowWapper;
        }
        taskFlowWapper.setTaskFlow(taskFlow);
        taskFlowWapper.setStatus(TaskStatusEnum.getTaskStatusByKey(taskFlow.getTaskStatus()));
        return taskFlowWapper;
    }

    private ManualTaskWapper mappingTask(Integer manualOrderId, OrderTaskEnum taskEnum) {
        ManualTaskWapper taskWapper = new ManualTaskWapper();
        ManualTask task = taskMapper.selectByManualOrderIdAndTaskCode(manualOrderId, taskEnum.getKey());
        if (task == null) {
            taskWapper = createTask(manualOrderId, taskEnum);
            return taskWapper;
        }
        taskWapper.setTask(task);
        taskWapper.setTaskEnum(taskEnum);
        return taskWapper;
    }
    
    /**
     * 查询全部任务节点
     */
    public Map<Integer, ManualTaskVo> getAllTask(Integer manualOrderId) {
        Map<Integer, ManualTaskVo> allTask = new HashMap<Integer, ManualTaskVo>();

        Map<ManualTaskWapper, ManualTaskFlowWapper> taskFlows = fetchTaskFlow(manualOrderId);
        
        Iterator<ManualTaskWapper> taskIt = taskFlows.keySet().iterator();
        while (taskIt.hasNext()) {
            ManualTaskWapper taskWapper = taskIt.next();
            ManualTaskVo taskVo = new ManualTaskVo();
            taskVo.setManualOrderId(manualOrderId);
            taskVo.setTaskCode(taskWapper.getTaskCode());
            taskVo.setTaskName(taskWapper.getTaskName());
            taskVo.setUrl(taskWapper.getUrl());
            ManualTaskFlowWapper taskFlowWapper = taskFlows.get(taskWapper);
            taskVo.setTaskStatusCode(taskFlowWapper.getStatus().getKey());
            taskVo.setTaskStatus(taskFlowWapper.getStatus().getName());
            if(taskFlowWapper.getStatus().getKey().equals(TaskStatusEnum.ENABLE.getKey())) {
                taskVo.setCurrentTask(true);
            } else {
                taskVo.setCurrentTask(false);
            }
            allTask.put(taskWapper.getTaskCode(), taskVo);
        }
        return allTask;
    }

    /**
     * 查询当前任务节点
     */
    public ManualTaskVo getCurrentTask(Integer manualOrderId) {
        ManualTaskVo taskVo = new ManualTaskVo();
        Map<ManualTaskWapper, ManualTaskFlowWapper> taskFlows = fetchTaskFlow(manualOrderId);
        ManualTaskWapper taskWapper = getCurrentTask(taskFlows);
        taskVo.setManualOrderId(manualOrderId);
        taskVo.setTaskCode(taskWapper.getTaskCode());
        taskVo.setTaskName(taskWapper.getTaskName());
        taskVo.setUrl(taskWapper.getUrl());
        taskVo.setTaskStatusCode(taskFlows.get(taskWapper).getStatus().getKey());
        taskVo.setTaskStatus(taskFlows.get(taskWapper).getStatus().getName());
        return taskVo;
    }
    
    /**
     * 查询指定任务节点的状态
     * @param orderId
     * @param taskEnum
     * @return
     */
    public ManualTaskVo getTaskStatus(Integer manualOrderId, OrderTaskEnum taskEnum) {
        ManualTaskVo taskVo = new ManualTaskVo();
        Map<ManualTaskWapper, ManualTaskFlowWapper> taskFlows = fetchTaskFlow(manualOrderId);
        ManualTaskWapper taskWapper = getTaskNode(taskFlows, taskEnum);
        taskVo.setManualOrderId(manualOrderId);
        taskVo.setTaskCode(taskWapper.getTaskCode());
        taskVo.setTaskName(taskWapper.getTaskName());
        taskVo.setTaskStatus(taskFlows.get(taskWapper).getStatus().getName());
        return taskVo;
    } 

    private ManualTaskWapper getCurrentTask(Map<ManualTaskWapper, ManualTaskFlowWapper> taskFlows) {
        Iterator<ManualTaskWapper> taskIt = taskFlows.keySet().iterator();
        while (taskIt.hasNext()) {
            ManualTaskWapper taskWapper = taskIt.next();
            if (!taskFlows.get(taskWapper).getStatus().equals(TaskStatusEnum.FINISH)) {
                return taskWapper;
            }
        }
        // no task has bean finished. then return the first task.
        return getTaskNode(taskFlows, OrderTaskEnum.ORDER);
    }
}
