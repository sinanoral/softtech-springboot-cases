import { useMutation, useQuery } from "react-query";
import { BASE_URL, URL } from "../../../api/constants";
import { HttpVerb, getToken, SuccessResponse, ErrorResponse } from "../common";
import { Product } from "./product";

interface UpdateProduct {
  id: number;
  name: string;
  categoryId: number;
  price: number;
}

async function updateProductById(requestBody: UpdateProduct) {
  const requestOptions = {
    method: HttpVerb.PUT,
    headers: {
      "Content-Type": "application/json",
      Authorization: `Bearer ${getToken()}`,
    },
    body: JSON.stringify(requestBody),
  };

  return await fetch(
    BASE_URL + URL.PRODUCTS + "/" + requestBody.id,
    requestOptions
  ).then((res) => res.json());
}

export const useUpdateProductById = () => {
  return useMutation<SuccessResponse<Product>, ErrorResponse, UpdateProduct>(
    "product",
    updateProductById
  );
};
