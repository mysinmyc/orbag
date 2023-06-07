import { SerializableFieldGroup } from "@/generated/client";

export function isFieldGroupEmpty (fieldGroup: SerializableFieldGroup): boolean {
    return fieldGroup.booleanFields?.length== 0 &&
        fieldGroup.configurationItemReferenceFields?.length== 0 &&
        fieldGroup.enumFields?.length== 0 &&fieldGroup.numericFields?.length== 0 &&
        fieldGroup.stringFields?.length ==0;
}