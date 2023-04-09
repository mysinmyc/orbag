import axios from "axios"
import { ConfigurationItemReference } from "./reference"
import { SerializableTable } from "./data"


export type SerializableView = {
    identifier: string,
    displayLabel: string
}


export type GetAvailableViewsRequest = {
    targetCi: ConfigurationItemReference;
}


export type GetAvailableViewsResponse = {
    availableViews: Array<SerializableView>
}

export type BindViewRequest = {
    targetCi: ConfigurationItemReference,
    view: SerializableView
}

export type BindViewResponse = {
    resultTable: SerializableTable
}

export function getAvailableViews(targetCi: ConfigurationItemReference): Promise<Array<SerializableView>> {
    return new Promise<Array<SerializableView>>((resolve,reject)=>{
        const request:GetAvailableViewsRequest= { targetCi: targetCi};
        axios.post<GetAvailableViewsResponse>("/api/view/getAvailable",request).then(r=>
            resolve(r.data.availableViews)
        ).catch(reason=> reject(reason));
    });
}

export function bindView(view:SerializableView, targetCi: ConfigurationItemReference): Promise<SerializableTable> {
    const request:BindViewRequest= { view:view,targetCi: targetCi, };
    return new Promise<SerializableTable>((resolve,reject)=>{
        axios.post<BindViewResponse>("/api/view/bind",request).then(r=>
            resolve(r.data.resultTable)
        ).catch(reason=> reject(reason));
    })
}