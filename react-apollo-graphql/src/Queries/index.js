import { gql } from 'apollo-boost';

export const GET_TOURS = gql`
  {
    allTours {
      id
      name
      type
      description
      agency {
        name
        rating
      }
    }
  }
`;

export const VIEW_TOURS = gql`
  query ($id: ID!){
    tour(id: $id) {
      id
      name
      description
      type
      agency{
        name
        rating
      }
    }
  }
`;

export const ADD_TOUR = gql`
  mutation($name: String, $email: String, $job_title: String) {
    createTour (name: $name, email: $email, job_title: $job_title)
  }
`;

export const EDIT_TOUR = gql`
  mutation($id: Int, $name: String, $email: String, $job_title: String) {
    updateTourInfo (id: $id, name: $name, email: $email, job_title: $job_title)
  }
`;

export const DELETE_TOUR = gql`
  mutation($id: Int) {
    deleteTour(id: $id)
  }
`
