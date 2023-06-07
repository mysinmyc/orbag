import { ConfigurationItemReference, SerializableAction, SubmitActionResponse } from "@/generated/client";
import {Subject, Subscription } from "rxjs";


const _editCiSubject = new Subject<ConfigurationItemReference>();

export function smartEditConfigurationItem(ci:ConfigurationItemReference):void {
    _editCiSubject.next(ci);
}

export function subscribeSmartEditConfigurationItem( callback: (_:ConfigurationItemReference) => void ): Subscription {
    return _editCiSubject.asObservable().subscribe(callback);
}

export type SmartActionEntry = {
    action: SerializableAction
    sourceCi?: ConfigurationItemReference
    cis: Array<ConfigurationItemReference>
    callback?: (_:SubmitActionResponse) => void
}

const _executeActionSubject= new Subject<SmartActionEntry>();


export function smartSubmitAction(actionToSubmit:SmartActionEntry):void {
    _executeActionSubject.next(actionToSubmit);
}

export function subscribeSmartSubmitAction(callback:(_:SmartActionEntry)=>void):Subscription {
    return _executeActionSubject.asObservable().subscribe(callback);
}


