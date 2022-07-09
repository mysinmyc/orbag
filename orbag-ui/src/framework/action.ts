import axios from "axios"
import { ConfigurationItemReference } from "./common"

export type GetAvailablActionRequest = {
    targetCis: Array<ConfigurationItemReference>
}

export type SerializableAction = {
    identifier: string,
    displayLabel: string
}

export type GetAvailableActionResponse = {
    availableActions: Array<SerializableAction>
}


export type SubmitActionRequest = {
    action: SerializableAction,
    targetCis: Array<ConfigurationItemReference>
}

export type SubmitActionResponse = {
    jobId: string,
    message: string
}

export function getAvailableActions(cis: Array<ConfigurationItemReference>): Promise<Array<SerializableAction>> {
    return new Promise<Array<SerializableAction>>((resolve,reject)=>{
        const request:GetAvailablActionRequest = { targetCis: cis};
        axios.post<GetAvailableActionResponse>("/api/action/getAvailable", request).then(r=>
            resolve(r.data.availableActions)
        ).catch(reason=> reject(reason));
    })
}

export function submitAction(action:SerializableAction, cis: Array<ConfigurationItemReference>): Promise<SubmitActionResponse> {
    return new Promise<SubmitActionResponse>((resolve,reject)=>{
        const request:SubmitActionRequest = { action:action, targetCis: cis};
        axios.post<SubmitActionResponse>("/api/action/submit", request).then(r=>
            resolve(r.data)
        ).catch(reason=> reject(reason));
    })
}