import { useQuery } from "react-query";
import { BASE_URL, URL } from "../../../api/constants";
import { HttpVerb, getToken, SuccessResponse, ErrorResponse } from "../common";
import { Product } from "./product";

async function getAllProductsByCategoryId(id: number) {
  const requestOptions = {
    method: HttpVerb.GET,
    headers: {
      "Content-Type": "application/json",
      Authorization: `Bearer ${getToken()}`,
    },
  };

  return await fetch(BASE_URL + URL.PRODUCTS + "/" + id, requestOptions).then(
    (res) => res.json()
  );
}

export const useGetAllProductsByCategoryId = (id: number) => {
  return useQuery<SuccessResponse<Product>, ErrorResponse>("product", () =>
    getAllProductsByCategoryId(id)
  );
};
