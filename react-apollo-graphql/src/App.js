import React from 'react';
import { useQuery } from '@apollo/react-hooks';
import { GET_TOURS, VIEW_TOURS } from "./Queries";
import { Card, CardBody, CardHeader, CardSubtitle, Spinner } from 'reactstrap';

function App() {
  const getAllTours = useQuery(GET_TOURS);
  const tourInfo = useQuery(VIEW_TOURS, { variables: { id: 2 }});
  if (getAllTours.loading || tourInfo.loading) return <Spinner color="dark" />;
  if (getAllTours.error || tourInfo.error) return <React.Fragment>Error :(</React.Fragment>;

  return (
    <div className="container">
      <Card>
        <CardHeader>Query - Displaying all data</CardHeader>
        <CardBody>
          <pre>
            {JSON.stringify(getAllTours.data, null, 2)}
          </pre>
        </CardBody>
      </Card>
      <Card>
        <CardHeader>Query - Displaying data with args</CardHeader>
        <CardBody>
          <CardSubtitle>Viewing a tour by id</CardSubtitle>
          <pre>
            {JSON.stringify(tourInfo.data, null, 2)}
          </pre>
        </CardBody>
      </Card>
    </div>
  )
}

export default App;
