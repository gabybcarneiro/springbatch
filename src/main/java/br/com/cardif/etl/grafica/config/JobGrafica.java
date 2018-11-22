package br.com.cardif.etl.grafica.config;

import br.com.cardif.etl.grafica.dao.ExternalServiceDAO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JobGrafica {

    @Autowired
    ExternalServiceDAO externalServiceDAO;

    private static final Logger LOGGER = Logger.getLogger(JobGrafica.class);

    public List<String> getBonecosForExtract(){
        return externalServiceDAO.getBonecosForExtract();
    }
}



