package br.com.moip.webhook.manager.component;

import java.io.File;
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

import org.springframework.stereotype.Service;

import br.com.moip.webhook.manager.to.Dashboard;
import br.com.moip.webhook.manager.to.Log;

@Service
public class DashboardComponent {

	public Dashboard readLogFile() throws Exception {
		
		File file = new File("./log.txt");
		List<Log> logsList = new ArrayList<Log>();
		Map<String, Integer> sumStatus = new TreeMap<String, Integer>();

		URI uri = file.toURI();

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

		return new Dashboard(sortAndSelect(logsList, 3), sumStatus);
	}
	
	private List<Log> sortAndSelect(List<Log> logsList, int portion) {
		Collections.sort(logsList, new Comparator<Log>() {
			@Override
			public int compare(Log o1, Log o2) {
				return o2.getSum().compareTo(o1.getSum());
			}
		});
		
		return logsList.subList(0, portion);
	}
	
	private void addToLogsList(List<Log> logsList, String url) {
		Log log = new Log(url);
		
		if (logsList.contains(log)) {
			int currentSum = logsList.get(logsList.indexOf(log)).getSum();
			logsList.get(logsList.indexOf(log)).setSum(++currentSum);
			
		} else {
			log.setSum(1);
			logsList.add(log);
		}
	}
	
	private void addToSumStatus(Map<String, Integer> sumStatus, String status) {
		if (sumStatus.containsKey(status)) {
			int currentSum = sumStatus.get(status);
			sumStatus.put(status, ++currentSum);
		} else {
			sumStatus.put(status, 1);
		}
	}
}
