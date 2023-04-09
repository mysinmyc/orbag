import axios from "axios"

export type WhoAmIResponse = {
    username: string,
    authorities: Array<string>
}


export function whoami(): Promise<WhoAmIResponse> {
    return new Promise<WhoAmIResponse>((resolve,reject) =>{
            axios.get<WhoAmIResponse>("/api/security/whoami").then(r=> resolve(r.data)).catch(reason=> reject(reason));
    })
}
