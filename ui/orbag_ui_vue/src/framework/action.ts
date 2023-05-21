import axios from "axios"
import { ConfigurationItemReference } from "./reference"
import { SerializableFieldGroup } from "./input"

export type SerializableAction = {
    identifier: string,
    displayLabel: string
}

export type ActionInputBase = {
    sourceCi?:ConfigurationItemReference,
    targetCis: Array<ConfigurationItemReference>
}

export type GetAvailableActionsRequest = ActionInputBase

export type GetAvailableActionsResponse = {
    availableActions: Array<SerializableAction>
}

export type BuildActionTemplateRequest = ActionInputBase & {
    action: SerializableAction
}

export type SubmitActionRequest = ActionInputBase & {
    action: SerializableAction,
    parameters: SerializableFieldGroup
}

export type ValidationError = {
    field?: string,
    error: string
}

export type SubmitActionResponse = {
    consequences: string,
    jobId: string,
    message: string,
    requestValid: boolean,
    validationErrors: Array<ValidationError>
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

export function buildActionTemplateWithSource( action:SerializableAction,sourceCi: ConfigurationItemReference|undefined, cis: Array<ConfigurationItemReference>): Promise<SubmitActionRequest> {
    return new Promise<SubmitActionRequest>((resolve,reject)=>{
        const request:BuildActionTemplateRequest = { action:action, sourceCi: sourceCi,  targetCis: cis};
        axios.post<SubmitActionRequest>("/api/action/buildExecutionTemplate", request).then(r=>
            resolve(r.data)
        ).catch(reason=> reject(reason));
    })
}

export function buildActionTemplate(action:SerializableAction, cis: Array<ConfigurationItemReference>): Promise<SubmitActionRequest> {
    return buildActionTemplateWithSource(action,undefined,cis);
}

export function submitAction(request:SubmitActionRequest): Promise<SubmitActionResponse> {
    return new Promise<SubmitActionResponse>((resolve,reject)=>{
        axios.post<SubmitActionResponse>("/api/action/submit", request).then(r=>
            resolve(r.data)
        ).catch(reason=> reject(reason));
    });
}