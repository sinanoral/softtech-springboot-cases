import { useMutation } from "react-query";
import { BASE_URL, URL } from "../../../api/constants";
import { ErrorResponse } from "../common";

interface LoginRegister {
  userName: string;
  password: string;
  name: string;
  surname: string;
}

interface RegisterResponse {
  data: {
    id: number;
    name: string;
    surname: string;
    userName: string;
  };
  message: string;
  responseDate: Date;
  success: boolean;
}

async function register(requestBody: LoginRegister) {
  const options = {
    method: "post",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(requestBody),
  };

  return await fetch(BASE_URL + URL.AUTH + "/register", options)
    .then((res) => res.json())
    .catch((err) => err.json());
}

export const useRegister = () => {
  return useMutation<RegisterResponse, ErrorResponse, LoginRegister>(
    "register",
    register
  );
};
