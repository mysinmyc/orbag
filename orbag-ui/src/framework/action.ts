import axios from "axios"
import { ConfigurationItemReference } from "./reference"

export type GetAvailableActionsRequest = {
    sourceCi?:ConfigurationItemReference,
    targetCis: Array<ConfigurationItemReference>
}

export type SerializableAction = {
    identifier: string,
    displayLabel: string
}

export type GetAvailableActionsResponse = {
    availableActions: Array<SerializableAction>
}


export type SubmitActionRequest = {
    sourceCi?:ConfigurationItemReference,
    action: SerializableAction,
    targetCis: Array<ConfigurationItemReference>
}

export type SubmitActionResponse = {
    consequences: string,
    jobId: string,
    message: string
}

export function getAvailableActionsWithSource(sourceCi: ConfigurationItemReference|undefined,targetCis: Array<ConfigurationItemReference>): Promise<Array<SerializableAction>> {
    return new Promise<Array<SerializableAction>>((resolve,reject)=>{
        const request:GetAvailableActionsRequest = { sourceCi: sourceCi, targetCis: targetCis};
        axios.post<GetAvailableActionsResponse>("/api/action/getAvailable", request).then(r=>
            resolve(r.data.availableActions)
        ).catch(reason=> reject(reason));
    })
}

export function getAvailableActions(targetCis: Array<ConfigurationItemReference>): Promise<Array<SerializableAction>> {
    return getAvailableActionsWithSource(undefined,targetCis);
}

export function submitActionWithSource(sourceCi: ConfigurationItemReference|undefined, action:SerializableAction, cis: Array<ConfigurationItemReference>): Promise<SubmitActionResponse> {
    return new Promise<SubmitActionResponse>((resolve,reject)=>{
        const request:SubmitActionRequest = { sourceCi: sourceCi, action:action, targetCis: cis};
        axios.post<SubmitActionResponse>("/api/action/submit", request).then(r=>
            resolve(r.data)
        ).catch(reason=> reject(reason));
    })
}

export function submitAction(action:SerializableAction, cis: Array<ConfigurationItemReference>): Promise<SubmitActionResponse> {
    return submitActionWithSource(undefined,action,cis);
}