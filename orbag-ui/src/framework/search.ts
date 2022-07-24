import axios from "axios";
import { SerializableTable } from "./data";
import { SerializableFieldGroup } from "./input"

export type SearchRequest = {
    configurationItemName: string,
    parameters: SerializableFieldGroup
    resultType: string;
}

export function getSearchTemplate(configurationItemType:string):Promise<SearchRequest> {
    return new Promise<SearchRequest>( (resolve,reject) =>{
        axios.get<SearchRequest>("/api/search/template/"+configurationItemType).then(r => resolve(r.data)).catch(reason => reject(reason));
    })
}

export function executeSearch(request: SearchRequest):Promise<SerializableTable> {
    return new Promise<SerializableTable>( (resolve,reject) =>{
        axios.post<SerializableTable>("/api/search/execute",request).then(r => resolve(r.data)).catch(reason => reject(reason));
    })
}