import { createContext, useContext } from "react";
import bankStore from "./BankStore";

const store = {
  bankStore: bankStore(),
};

export const StoreContext = createContext(store);

export const useStore = () => {
  return useContext<typeof store>(StoreContext);
};

export default store;