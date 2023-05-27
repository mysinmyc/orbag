import axios from "axios"
import { Subject, Subscription } from "rxjs";
import VueRouter from "vue-router"

export type LoginRequest = {
    userName: string
    password: string
    persistent: boolean
}

export type LoginResponse = {
    token: string,
    userName: string,
    authorities: Array<string>   
}

export type WhoAmIResponse = {
    userName: string,
    authorities: Array<string>
}


export function whoami(): Promise<WhoAmIResponse> {
    return new Promise<WhoAmIResponse>((resolve,reject) =>{
        axios.get<WhoAmIResponse>("/api/authentication/whoami").then(r=> resolve(r.data) ).catch(reason=> {
            reject(reason);
        });
    })
}


export function checkLoggedUser(): Promise<boolean> {
    return new Promise<boolean>((resolve,) =>{
        whoami().then( (r)=>  {
            _LoginSubject.next({user: r.userName, logged:true} as LoginEvent);
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
                _LoginSubject.next({ userName:"anonymous", logged:false} as LoginEvent);
            }
            return Promise.reject(error);
        });
}

export function login(userName:string, password:string): Promise<boolean> {
    return new Promise<boolean>((resolve,reject) =>{
        const loginRequest = { userName: userName, password: password, persistent: true} as LoginRequest;
        axios.post("/api/authentication/login", loginRequest).then( ()=> {
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
