
export type SerializableColumn = {
    name: string;
    displayLabel: string;
    type: string;
}

export type SerializableRow = {
    [key: string]: any;
}

export type SerializableTable = {
    columns: Array<SerializableColumn>;
    rows: Array<SerializableRow>;
}