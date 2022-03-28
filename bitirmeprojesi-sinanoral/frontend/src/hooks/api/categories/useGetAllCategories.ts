import { Category } from "./category";
import { ErrorResponse, getToken, HttpVerb, SuccessResponse } from "../common";
import { useQuery } from "react-query";
import { BASE_URL, URL } from "../../../api/constants";

async function getAllCategories() {
  const requestOptions = {
    method: HttpVerb.GET,
    headers: {
      "Content-Type": "application/json",
      Authorization: `Bearer ${getToken()}`,
    },
  };

  return await fetch(BASE_URL + URL.CATEGORIES, requestOptions).then((res) =>
    res.json()
  );
}

export const useGetAllCategories = () => {
  return useQuery<SuccessResponse<Category[]>, ErrorResponse>(
    "getAllCategories",
    getAllCategories
  );
};
