import { useMutation, useQueryClient } from "react-query";
import { BASE_URL, URL } from "../../../api/constants";
import { HttpVerb, getToken, SuccessResponse, ErrorResponse } from "../common";

async function deleteProductById(id: number) {
  const requestOptions = {
    method: HttpVerb.DELETE,
    headers: {
      "Content-Type": "application/json",
      Authorization: `Bearer ${getToken()}`,
    },
  };

  return await fetch(BASE_URL + URL.PRODUCTS + "/" + id, requestOptions).then(
    (res) => res.json()
  );
}

export const useDeleteProductById = () => {
  const queryClient = useQueryClient();
  return useMutation<SuccessResponse<void>, ErrorResponse, number>(
    "product",
    deleteProductById,
    {
      onSuccess: () => {
        queryClient.invalidateQueries("product");
      },
    }
  );
};
