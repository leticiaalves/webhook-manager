package br.com.moip.webhook.manager.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.moip.webhook.manager.component.DashboardComponent;
import br.com.moip.webhook.manager.to.Dashboard;

@RestController
@RequestMapping("/api")
public class DashboardController {
	
	@Autowired
	private DashboardComponent component;

    @RequestMapping("/dashboard")
    public Dashboard dashboard() throws Exception {
        return component.readLogFile();
    }
}
