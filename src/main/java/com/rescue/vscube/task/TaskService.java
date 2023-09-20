package com.rescue.vscube.task;

import com.rescue.vscube.TaskTeam.TaskTeam;
import com.rescue.vscube.TaskTeam.TaskTeamRepository;
import com.rescue.vscube.agency.Agency;

import com.rescue.vscube.agency.AgencyRepository;
import com.rescue.vscube.event.Event;
import com.rescue.vscube.event.EventRepository;
import com.rescue.vscube.event.EventService;
import com.rescue.vscube.notification.NotificationService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private EventService eventService;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private NotificationService notificationService;

    public List<Task> getALLTasks() {
        return taskRepository.findAll();
    }

    public Task addTask(TaskDTO taskDTO) {
        Event event = eventRepository.findById(taskDTO.getEvent_id()).get();

        Task task =new Task();

        task.setEvent(event);
        task.setStatus(taskDTO.getStatus());
        task.setDescription(taskDTO.getDescription());

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
        TaskDTO taskDTO = new TaskDTO(taskId, task.get().getEvent().getId(), task.get().getDescription(),task.get().getTime_created(),task.get().getStatus(),
                agencies);
        return taskDTO;
    }

    public void addAgency(Long taskId,Long agencyId){
        Optional<Task> task = taskRepository.findById(taskId);

        Optional<Agency> agency2 = agencyRepository.findById(agencyId);
        Agency agency = agency2.orElse(null);

        TaskTeam taskTeam = new TaskTeam();
        taskTeam.setTask(task.get());
        taskTeam.setAgency(agency);
        taskTeamRepository.save(taskTeam);

        String message = "Hello you have been assigned to task " + task.get().getDescription() +" in the event "+task.get().getEvent().getName() ;

        notificationService.sendMessage(agency.getPhone(),message);
    }

}
