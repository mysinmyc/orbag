import { ConfigurationItemReference } from "./reference"

export type InputFieldBase<T> = {
    name: string,
    displayLabel: string
    //type: string,
    value: T ,
    changed: boolean,
    readOnly: boolean
}

export type ConfigurationItemReferenceField = InputFieldBase<ConfigurationItemReference> & {
    configurationItemType: string
}

export type EnumField = InputFieldBase<string> & {
    allowedValues: Array<string>
}

export type SerializableFieldGroup = {
    stringFields: Array<InputFieldBase<string>>
    booleanFields: Array<InputFieldBase<boolean>>
    numericFields: Array<InputFieldBase<number>>
    enumFields: Array<EnumField>
    configurationItemReferenceFields: Array<ConfigurationItemReferenceField>
}

export function isFieldGroupEmpty (fieldGroup: SerializableFieldGroup): boolean {
    return fieldGroup.booleanFields.length== 0 &&
        fieldGroup.configurationItemReferenceFields.length== 0 &&
        fieldGroup.enumFields.length== 0 &&fieldGroup.numericFields.length== 0 &&
        fieldGroup.stringFields.length ==0;
}