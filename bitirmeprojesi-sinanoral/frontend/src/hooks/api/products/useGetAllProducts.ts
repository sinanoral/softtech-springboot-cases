import { Product } from "./product";
import { ErrorResponse, getToken, HttpVerb, SuccessResponse } from "../common";
import { useQuery } from "react-query";
import { BASE_URL, URL } from "../../../api/constants";

async function getAllProducts() {
  const requestOptions = {
    method: HttpVerb.GET,
    headers: {
      "Content-Type": "application/json",
      Authorization: `Bearer ${getToken()}`,
    },
  };

  return await fetch(BASE_URL + URL.PRODUCTS, requestOptions).then((res) =>
    res.json()
  );
}

export const useGetAllProducts = () => {
  return useQuery<SuccessResponse<Product[]>, ErrorResponse>(
    "product",
    getAllProducts
  );
};
