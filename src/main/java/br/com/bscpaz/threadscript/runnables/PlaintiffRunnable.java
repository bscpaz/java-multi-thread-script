package br.com.bscpaz.threadscript.runnables;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.bscpaz.threadscript.dtos.PlaintiffDto;
import br.com.bscpaz.threadscript.entities.Lawsuit;
import br.com.bscpaz.threadscript.repositories.LawsuitRepository;

/**
 * @author Bruno Paz - (bscpaz)
 */

@Component
@Scope("prototype")
public class PlaintiffRunnable implements Runnable {

    private CountDownLatch countDownLatch = null;
    private PlaintiffDto plaintiffDto = null;

    @Autowired
    private LawsuitRepository lawsuitRepository;

    public void setCountDownLatch(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }
	
    public void setPlaintiffDto(PlaintiffDto plaintiffDto) {
        this.plaintiffDto = plaintiffDto;
    }

    @Override
    public void run() {
        System.out.println(String.format("[%s] Processing the '%s'...", 
                Thread.currentThread().getName(), this.plaintiffDto.getPlaintiff()));

        generateScriptByPlaintiff();
    }

    private void generateScriptByPlaintiff() {
        try {
            List<Lawsuit> lawsuits = lawsuitRepository
                .findActivedLawsuitByPlaintiff(this.plaintiffDto.getPlaintiff());
        
            for (Lawsuit lawsuit: lawsuits) {
                //TODO: generate a .SQL script
                System.out.println(lawsuit);
            }
            this.plaintiffDto.setSuccess(true);
        } catch (Exception e) {
            this.plaintiffDto.setSuccess(false);
            e.printStackTrace();
        } finally {
            countDownLatch.countDown();
        }        
    }
}
