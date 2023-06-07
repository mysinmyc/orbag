import { ActionControllerApi, AuthenticationControllerApi, ConfigResponse, Configuration, ConfigurationParameters, CreateControllerApi, ListControllerApi, LoginRequest, MetadataControllerApi, ReferenceControllerApi, SearchControllerApi, UpdateControllerApi, ViewControllerApi } from "@/generated/client";


let _appConfig: ConfigResponse | undefined;

let _myMyHttpClientInstance: MyHttpClient | undefined ;

export function getAppConfig():ConfigResponse {
    return _appConfig!;
}

export function getServerAddress():string {
  return getAppConfig().address!;
}

export function loadConfig( callback?: ()=>void) {
  fetch("/public/config.json").then(function (response){
    response.json().then(json =>{
      _appConfig=json;
      _myMyHttpClientInstance=new MyHttpClient(_appConfig!);
      if (callback !=undefined){
        callback!();
      }
    });
  });
}


export class MyHttpClient {

  protected _authenticationToken:string | undefined;

  constructor(protected appConfig:ConfigResponse) {

  }

  protected get configuration():Configuration {
    return new Configuration({ basePath: this.appConfig.address, accessToken: this._authenticationToken }  as ConfigurationParameters );
  }

  public get authenticationApi(): AuthenticationControllerApi {
    return new AuthenticationControllerApi(this.configuration);
  }

  public get metadataApi(): MetadataControllerApi {
    return new MetadataControllerApi(this.configuration);
  }

  public get searchApi(): SearchControllerApi {
    return new SearchControllerApi(this.configuration);
  }

  public get actionApi(): ActionControllerApi {
    return new ActionControllerApi(this.configuration);
  }
  
  public get viewApi(): ViewControllerApi {
    return new ViewControllerApi(this.configuration);
  }

  public get updateApi(): UpdateControllerApi {
    return new UpdateControllerApi(this.configuration);
  }

  public get createApi(): CreateControllerApi {
    return new CreateControllerApi(this.configuration);
  }

  public get referenceApi(): ReferenceControllerApi{
    return new ReferenceControllerApi(this.configuration);
  }

  public get listApi(): ListControllerApi {
    return new ListControllerApi(this.configuration);
  }
  
  public async login(userName:string, password: string): Promise<boolean>  {
    this._authenticationToken =undefined;
    const response = await this.authenticationApi.login({userName: userName, password: password} as LoginRequest);
    this._authenticationToken =response.data!.token;
    return true;
  }
}

export function myHttpClient(): MyHttpClient {
    return _myMyHttpClientInstance!;
}