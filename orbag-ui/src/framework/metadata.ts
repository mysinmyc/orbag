import axios from "axios";

export type ClassModel = {
    configurationItemDescriptors: Array<ConfigurationItemDescriptor>;
}

export type ConfigurationItemPropertyDescriptor = {
    name: string;
    displayLabel: string;
}

export type ConfigurationItemDescriptor = {
    name: string;
    category: string;
    properties?: Array<ConfigurationItemPropertyDescriptor>;
    allowCreation: boolean;
    displayLabel: string;
}

export function getClassModel(): Promise<ClassModel> {
    return new Promise<ClassModel>((resolve,reject) =>{
        axios.get<ClassModel>("/api/metadata").then(r=> resolve(r.data)).catch(reason=> reject(reason));
    });
}


export function getClassMetadata(configurationItemType:string, includeProperties:boolean): Promise<ConfigurationItemDescriptor> {
    return new Promise<ConfigurationItemDescriptor>((resolve,reject) =>{
        axios.get<ConfigurationItemDescriptor>("/api/metadata/"+configurationItemType+"?properties="+includeProperties).then(r=> resolve(r.data)).catch(reason=> reject(reason));
    });
}