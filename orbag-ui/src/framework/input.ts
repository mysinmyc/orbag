
export type InputFieldBase = {
    name: string,
    displayLabel: string
    type: string,
    value: any  
}

export type SerializableFieldGroup = {
    fields: Array<InputFieldBase>
}