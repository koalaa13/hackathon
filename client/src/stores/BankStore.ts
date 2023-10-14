import { makeAutoObservable } from "mobx";
import { Task } from "../interfaces/Task";

const bankStore = () => {
    return makeAutoObservable({
        id: 1,
        name: 'bank',
        address: 'address',
        latitude: 0,
        longitude: 0,
        tasks: [] as Task[],
        load: [] as number[]
    });
};

export default bankStore;