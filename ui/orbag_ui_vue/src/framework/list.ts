
import { ConfigurationItemReference } from "./reference";
import axios from "axios";

export type ConfigurationItemList = {
    cis: Array<ConfigurationItemReference>
}


export function listConfigurationItems(configurationItemType:string):Promise<ConfigurationItemList> {
    return new Promise<ConfigurationItemList>( (resolve,reject) =>{
        axios.get<ConfigurationItemList>("/api/list/"+configurationItemType).then(r => resolve(r.data)).catch(reason => reject(reason));
    })
}