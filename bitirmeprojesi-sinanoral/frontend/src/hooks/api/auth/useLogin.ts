import { useMutation } from "react-query";
import { BASE_URL, URL } from "../../../api/constants";
import { ErrorResponse } from "../common";

interface LoginRequest {
  userName: string;
  password: string;
}

interface LoginResponse {
  data: {
    token: string;
  };
  responseDate: Date;
  message: string | null;
  success: boolean;
}

async function login(requestBody: LoginRequest) {
  const options = {
    method: "post",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(requestBody),
  };

  return await fetch(BASE_URL + URL.AUTH + "/login", options)
    .then((res) => res.json())
    .catch((err) => err.json());
}

export const useLogin = () => {
  return useMutation<LoginResponse, ErrorResponse, LoginRequest>(
    "login",
    login
  );
};
