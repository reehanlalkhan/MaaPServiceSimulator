package com.mavenir.mbmp.abcservicesimulator;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@PropertySource("classpath:simulator.properties")
public class PropertiesLoader {

	@Value("#{${cards.count.array}}")
	private List<Integer> cardsCountList;

	@Value("#{${response.callback.array}}")
	private List<String> responseCallbbackList;

	private int cardsCountListCounter;
	private int currnetFlowCounter;
	private int responseCallbbackListCounter;

	public boolean checkIfResponseNeedsToBeSent() {
		log.debug("Before::currnetFlowCounter:{}, cardsCountListCounter:{}", currnetFlowCounter, cardsCountListCounter);
		int howManyFlows = cardsCountList.get(cardsCountListCounter);
		currnetFlowCounter++;
		if (howManyFlows == currnetFlowCounter) {
			currnetFlowCounter = 0;
			cardsCountListCounter++;
			log.debug("After(true)::currnetFlowCounter:{}, cardsCountListCounter:{}", currnetFlowCounter, cardsCountListCounter);
			return true;
		}
		log.debug("After(false)::currnetFlowCounter:{}, cardsCountListCounter:{}", currnetFlowCounter, cardsCountListCounter);
		return false;
	}

	public String getCallbackString() {
		String callback = responseCallbbackList.get(responseCallbbackListCounter++);
		log.debug("Callback:{}", callback);
		return callback;
	}
}
