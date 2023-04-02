import { ConfigurationItemReference } from "./reference"

export type InputFieldBase<T> = {
    name: string,
    displayLabel: string
    type: string,
    value: T ,
    changed: boolean,
    readOnly: boolean
}

export type configurationItemReferenceField = InputFieldBase<ConfigurationItemReference> & {
    configurationItemType: string
}

export type EnumField = InputFieldBase<String> & {
    allowedValues: string
}

export type SerializableFieldGroup = {
    stringFields: Array<InputFieldBase<string>>
    booleanFields: Array<InputFieldBase<boolean>>
    numericFields: Array<InputFieldBase<number>>
    enumFields: Array<EnumField>
    configurationItemReferenceFields: Array<InputFieldBase<ConfigurationItemReference>>
}