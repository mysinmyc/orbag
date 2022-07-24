
export type InputFieldBase = {
    name: string,
    displayLabel: string
    type: string,
    value: any ,
    changed: boolean,
    readoOnly: boolean
}

export type SerializableFieldGroup = {
    fields: Array<InputFieldBase>
}