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
    properties: Array<ConfigurationItemPropertyDescriptor>;
}

export function getClassModel(): Promise<ClassModel> {
    return new Promise<ClassModel>((resolve,reject) =>{
        axios.get<ClassModel>("/api/metadata").then(r=> resolve(r.data)).catch(reason=> reject(reason));
    });
}
