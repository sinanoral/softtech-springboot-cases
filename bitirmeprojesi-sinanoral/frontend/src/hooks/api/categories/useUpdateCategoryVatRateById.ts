import { Category } from "./category";
import { useMutation, useQuery } from "react-query";
import { BASE_URL, URL } from "../../../api/constants";
import { HttpVerb, getToken, SuccessResponse, ErrorResponse } from "../common";

interface UpdateCategory {
  vatRate: number;
}

async function updateCategoryVatRateById(
  id: number,
  requestBody: UpdateCategory
) {
  const requestOptions = {
    method: HttpVerb.PATCH,
    headers: {
      "Content-Type": "application/json",
      Authorization: `Bearer ${getToken()}`,
    },
    body: JSON.stringify(requestBody),
  };

  return await fetch(BASE_URL + URL.PRODUCTS + "/" + id, requestOptions).then(
    (res) => res.json()
  );
}

export const useUpdateCategoryVatRateById = (
  id: number,
  requestBody: UpdateCategory
) => {
  return useMutation<SuccessResponse<Category>, ErrorResponse>(
    "updateCategoryVatRateById",
    () => updateCategoryVatRateById(id, requestBody)
  );
};
