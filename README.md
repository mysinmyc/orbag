ORBAG
=====


Mockup semplificato di un'interfaccia di automazione

Al momento le azioni sono finte e non è implementata la sicurezza



## Componenti

Il server è una applicazione springboot implementata nel modulo *orbag-server* che espone delle api rest (/api) e un'interfaccia web 

L'interfaccia web è definita nel progetto *orbag-ui* in VUEjs 2 + bootstrap . Il risultato della compilazione presente nel folder [dist](orbag-ui/dist) va copiato nel folder [src/main/resources/public](orbag-server/src/main/resources/public) del progetto orbag-server 

I dati sono caricati in un H2 inmemory all'avvio e inizializzati con lo script sql [data.sql](orbag-impl/src/main/resources/data.sql)



## Come sviluppare il backend

Lo sviluppatore definisce tutte le entità in java all'interno del progetto *orbag-impl*

Al minimo un'entità deve essere così definita


```

@ConfigurationItem
@Entity
public class MiaEntita extends RootConfigurationItem {
}

```


Il server in fase di avvio legge tutte le entita standard e le registra nel [MetadataRegistry](orbag-core/src/main/java/orbag/metadata/MetadataRegistry.java)



E' possibile inoltre definire delle azioni associate alle Entities

```
@Component
@DisplayLabel("Install software")
public class InstallSoftware extends ConfigurationItemActionBase{

	@Override
	public boolean isAvailableFor(ActionRequest request) {
		return ActionUtils.areAllObjectsOfType(request.getTargetCis(), Server.class);
	}


	@Override
	public ActionResult execute(ActionRequest request) {
		ActionResult result= new ActionResult();
		result.setMessage("Software istalled on "+ request.getTargetCis().size()+" servers");
		result.setConsequences(ActionConsequences.UNDEFINED);
		return result;
	}

}

```

## Interfacce di ricerca


Esiste un'interfaccia di ricerca generica per tutte le entities che mostra come campi filtro tutti i field marcati con l'annotazione  [@Searcheable](orbag-core/src/main/java/orbag/search/Searcheable.java)

Ricerche più complesse richiedono l'implementazione di un [SearchExecutor](orbag-core/src/main/java/orbag/search/SearchExecutor.java). I search executors sono classi che forniscono i parametri per le ricerche e popoloano il risultato in una tabella. Un esempio è [BrewerySearchExecutor](orbag-impl/src/main/java/orbag/samples/brewery/BrewerySearchExecutor.java)


## Creazione ConfigurationItems

E' possibile creare nuove istanze di configuration items cliccando sul pulsante *Create new* presente sull'interfaccia di ricerca.

Normalmente la creazione di un configuration item richiede la valorizzazione di tutte le properties annotate con *@ConfigurationItemProperty(mandatoryForCreation=true)*

Nel caso in cui per la creazione del configuration item siano necessari parametri diversi o l'esecuzione di codice custom è possibile implementare un componente di tipo [ConfigurationItemWizard](orbag-core/src/main/java/orbag/create/ConfigurationItemWizard.java). Un esempio è [CreateServerGroupWizard](orbag-impl/src/main/java/orbag/samples/wizards/CreateServerGroupWizard.java)

Per evitare che sia possibile creare nuove istanze di un ConfigurationItem annotarlo con *@ConfigurationItem(allowCreation=false)*

## Esecuzione locale

In ambiente di sviluppo

E' possibile far partire il server eseguendo da eclipse il main della classe [ServerMain](orbag-server/src/main/java/orbag/server/ServerMain.java)

Il server di sviluppo è accessibile all'indirizzo http://localhost:8080

Se si vuole sviluppare lato frontend oltre ad avviare il server java in porta 8080 occorre posizionarsi su *orbag-ui* e eseguire in comando ` npm ci; npm run serve ` ; il comando *npm ci* è necessario solo la prima volta per scaricare le dipendenze . L'applicazione vue sarà accessibile all'url http://localhost:9090


Gli endpoint del server sono protetti. All'accesso verrà richiesta l'utenticazione. Alla richiesta di credenziali utilizzare *it_user* password *orbag*.
La lista degli utenti di test è definita in [CustomSecurityConfiguration](orbag-impl/src/main/java/orbag/samples/security/CustomSecurityConfiguration.java). 


## Compilazione ed esecuzione su container

E' possibile creare ed eseguire un container docker con il server 


docker build -t orbag .

docker run -d --rm -p8080:8080 orbag 



## Ringraziamenti

Alla ricerca di dati di esempio per testare il software ho trovato un'api pubblica [https://www.openbrewerydb.org](https://www.openbrewerydb.org/) che è stata inglobata con il solo scopo di fornire dati di esempio