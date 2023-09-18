package com.rescue.vscube.task;

import com.rescue.vscube.TaskTeam.TaskTeam;
import com.rescue.vscube.TaskTeam.TaskTeamRepository;
import com.rescue.vscube.agency.Agency;

import com.rescue.vscube.agency.AgencyRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskTeamRepository taskTeamRepository;

    @Autowired
    private AgencyRepository agencyRepository;

    public List<Task> getALLTasks() {
        return taskRepository.findAll();
    }

    public Task addTask(Task task) {

        return taskRepository.save(task);
    }

    public Task updateTask(Task task) {
        return taskRepository.save(task);

    }

    public TaskDTO findOne(Long taskId){
        Optional<Task> task = taskRepository.findById(taskId);
        List<Agency> agencies = new ArrayList<Agency>();
        List<TaskTeam> taskTeam = taskTeamRepository.findAllByTask(task.get());
        for(TaskTeam agency:taskTeam){
            agencies.add(agency.getAgency());
        }
        TaskDTO taskDTO = new TaskDTO(taskId, task.get().getEvent_id(), task.get().getDescription(),task.get().getTime_created(),task.get().getStatus(),
                agencies);
        return taskDTO;
    }

    public void addAgency(Long taskId,Long agencyId){
        TaskDTO taskDTO = findOne(taskId);

        Optional<Agency> agency2 = agencyRepository.findById(agencyId);
        Agency agency = agency2.orElse(null);

        TaskTeam taskTeam = new TaskTeam();
        Task task = new Task(taskDTO.getTask_id(),taskDTO.getEvent_id(),taskDTO.getDescription(),taskDTO.getTime_created(), taskDTO.getStatus());
        taskTeam.setTask(task);
        taskTeam.setAgency(agency);

        taskTeamRepository.save(taskTeam);
    }

}
