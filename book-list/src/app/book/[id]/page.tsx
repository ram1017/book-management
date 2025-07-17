import { getClient } from "@/lib/ssrApolloClient";
import { GET_BOOK_BY_ID } from "@/graphql/queries";
import BookDetailsClient from "@/components/BookDetialsClient";

export default async function BookPage(props: any) {
  const id = props.params.id;

  const { data } = await getClient().query({
    query: GET_BOOK_BY_ID,
    variables: { uniqueId: id },
  });

  return <BookDetailsClient initialData={data} />;
}
