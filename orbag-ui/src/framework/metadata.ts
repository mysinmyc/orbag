import axios from "axios";

export type ClassModel = {
    configurationItemDescriptors: Array<ConfigurationItemDescriptor>;
}

export type ConfigurationItemDescriptor = {
    name:string
}

export function getClassModel(): Promise<ClassModel> {
    return new Promise<ClassModel>((resolve,reject) =>{
        axios.get<ClassModel>("/api/metadata").then(r=> resolve(r.data)).catch(reason=> reject(reason));
    });
}
