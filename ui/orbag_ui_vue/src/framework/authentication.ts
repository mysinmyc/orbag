import axios from "axios"

export type LoginRequest = {
    userName: string
    password: string
    persistent: boolean
}

export type LoginResponse = {
    token: string
}

export type WhoAmIResponse = {
    username: string,
    authorities: Array<string>
}


export function whoami(): Promise<WhoAmIResponse> {
    return new Promise<WhoAmIResponse>((resolve,reject) =>{
            axios.get<WhoAmIResponse>("/api/authentication/whoami").then(r=> resolve(r.data)).catch(reason=> reject(reason));
    })
}

