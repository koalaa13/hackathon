import { Task } from "./Task";

export interface Bank {
    id: number;
    name: string;
    address: string;
    latitude: number;
    longitude: number;
    tasks?: Task[];
    load?: number[];
    isATM: boolean;
}