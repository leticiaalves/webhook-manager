package br.com.moip.webhook.manager;

import java.net.URI;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import br.com.moip.webhook.manager.to.Log;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration()
public class WebhookManagerApplicationTests {
	
	@Test
	public void readLogFile() throws Exception {
		
		List<Log> logsList = new ArrayList<Log>();
		List<Log> threeMost = new ArrayList<Log>();
		Map<String, Integer> sumStatus = new TreeMap<String, Integer>();
		
		URI uri = new ClassPathResource("log.txt").getURI();
		
		String urlRegex = "(request_to=\")(.*?)(\")";
		String codeRegex = "(response_status=\")(.*?)(\")";

		List<String> lines = Files.readAllLines(Paths.get(uri), Charset.forName("ISO-8859-1"));

		Pattern urlPattern = Pattern.compile(urlRegex);
		Pattern codePattern = Pattern.compile(codeRegex);
		
		for (String line : lines) {
			Matcher urlMatcher = urlPattern.matcher(line);
			Matcher codeMatcher = codePattern.matcher(line);
			
			if (urlMatcher.find() && codeMatcher.find()) {
				addToLogsList(logsList, urlMatcher.group(2));
				addToSumStatus(sumStatus, codeMatcher.group(2));
			}
		}
		
		Collections.sort(logsList, new Comparator<Log>() {
			@Override
			public int compare(Log o1, Log o2) {
				return o2.getSum().compareTo(o1.getSum());
			}
		});
		
		threeMost = logsList.subList(0, 3);
		
		for (Log l : logsList) {
			System.out.println(l);
		}
		
		System.out.println("The three most called: ");
		for (Log l : threeMost) {
			System.out.println(l);
		}
		
		System.out.println("Sum by Status");
		System.out.println(sumStatus);
		
	}	
	
	public void addToLogsList(List<Log> listaLogs, String url) {
		Log log = new Log(url);
		
		if (listaLogs.contains(log)) {
			int totalAtual = listaLogs.get(listaLogs.indexOf(log)).getSum();
			listaLogs.get(listaLogs.indexOf(log)).setSum(++totalAtual);
			
		} else {
			log.setSum(1);
			listaLogs.add(log);
		}
	}
	
	public void addToSumStatus(Map<String, Integer> quantidadePorStatus, String status) {
		if (quantidadePorStatus.containsKey(status)) {
			int totalAtual = quantidadePorStatus.get(status);
			quantidadePorStatus.put(status, ++totalAtual);
		} else {
			quantidadePorStatus.put(status, 1);
		}
	}
}
