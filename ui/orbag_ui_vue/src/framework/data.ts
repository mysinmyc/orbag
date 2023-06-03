
export type SerializableColumn = {
    name: string;
    displayLabel: string;
    type: string;
}

export type SerializableRow = {
    fields: SerializableFields;
}
export type SerializableFields ={
    [key: string]: any;
}

export type SerializableTable = {
    columns: Array<SerializableColumn>;
    rows: Array<SerializableRow>;
}