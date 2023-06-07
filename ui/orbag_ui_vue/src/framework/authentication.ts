
import axios from "axios"
import { Subject, Subscription } from "rxjs";
import VueRouter from "vue-router"
import { AuthenticationControllerApi, Configuration, LoginRequest, WhoAmIResponse } from "@/generated/client";
import { MyHttpClient, getServerAddress, myHttpClient } from "./client";





export function checkLoggedUser(): Promise<boolean> {
    return new Promise<boolean>((resolve,) =>{
        myHttpClient().authenticationApi.whoAmI().then( (r)=>  {
            _LoginSubject.next({user: r.data!.userName, logged:true} as LoginEvent);
            resolve(true);
        }
        );
    });
}

export function initAuthenticationFilter(_: VueRouter): void {
    console.log("init");
    axios.interceptors.response.use(
        response =>response,
        error => {    
            if (error.response.status === 403) {
             //   _LoginSubject.next({ userName:"anonymous", logged:false} as LoginEvent);
            }
            return Promise.reject(error);
        });
}

export function login(userName:string, password:string): Promise<boolean> {
    return new Promise<boolean>((resolve,reject) =>{
        myHttpClient().login(userName,password).then( ()=> {

            _LoginSubject.next({ user: userName, logged:true} as LoginEvent);
            resolve(true);
    }).catch((reason)=> {
        _LoginSubject.next({ logged:false} as LoginEvent);
        reject(reason);
    });
    });
}


export type LoginEvent = {
    user?: string
    logged: boolean
}

const _LoginSubject =  new Subject<LoginEvent>();


export function subscribeLoginEvent(callback:(_:LoginEvent)=>void):Subscription {
    return _LoginSubject.asObservable().subscribe(callback);
}
