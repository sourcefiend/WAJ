package hr.tvz.smolcic.hardwareapp.util.jobs;

import hr.tvz.smolcic.hardwareapp.DTOs.HardwareDTO;
import hr.tvz.smolcic.hardwareapp.service.HardwareService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.List;

public class AvailableHardwareJob extends QuartzJobBean {

    @Autowired
    private HardwareService hardwareService;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        System.out.println("Trenutno dostupan hardver:");
        System.out.println("-------------------------------");

        List<HardwareDTO> hardwareList = this.hardwareService.findAll();

        for (HardwareDTO hw : hardwareList) {
            if (hw.getStock() > 0) {
                System.out.println(hw.toString());
            }
        }
    }
}
