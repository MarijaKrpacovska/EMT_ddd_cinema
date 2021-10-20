import React, {useState} from "react";
import movies from "../../Movie/MovieList/movie";
import {Link} from 'react-router-dom';
import ReactPaginate from "react-paginate";

const ConfirmedReservations = (props) => {

    const [pageNumber, setPageNumber] = useState(0);
    const itemsPerPage = 8;
    const pageVisited = pageNumber * itemsPerPage;
    const pageCount = Math.ceil(props.reservations.length / itemsPerPage);

    const handlePageChange = ({selected}) => {
        setPageNumber(selected);
    }

    const displayTicketReservations = props.reservations
        .slice(pageVisited, pageVisited + itemsPerPage)
        .map((term) => {
            return (
                <tr>
                    <td>{term.id.id}</td>
                    <td>
                        <Link
                            onClick={() => { props.onCancelConfirmedReservation(term.id.id)}}
                            to={`/ticket/confirmedReservations`}>
                            Cancel reservation
                        </Link>
                    </td>
                </tr>
            );
        });

    return (
        <div className={"container mm-4 mt-5"}>
            <div className={"row"}>
                <h3 className={"text-danger"}>
                    Made reservations:
                </h3>
                <hr/>
                <div className={"table-responsive"}>
                    <table className={"table table-striped"}>
                        <thead>
                        <tr>
                            <th scope={"col"}>sales</th>
                            <th scope={"col"}>time</th>
                            <th scope={"col"}>ticket price</th>
                            <th scope={"col"}>movie id</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>

                        {displayTicketReservations}
                        </tbody>
                    </table>
                </div>
            </div>

            <div className="col mb-3">
                <ReactPaginate previousLabel={"back"}
                               nextLabel={"next"}
                               breakLabel={<a className={"page-link"} href="/#">...</a>}
                               breakClassName={'page-item'}
                               breakLinkClassName={'page-link'}
                               containerClassName={'pagination m-4 justify-content-center'}
                               pageClassName={'page-item'}
                               pageLinkClassName={'page-link'}
                               previousClassName={'page-item'}
                               previousLinkClassName={'page-link'}
                               nextClassName={'page-item'}
                               nextLinkClassName={'page-link'}
                               activeClassName={'active'}
                               pageCount={pageCount}
                               marginPagesDisplayed={3}
                               pageRangeDisplayed={5}
                               onPageChange={handlePageChange}/>
            </div>

        </div>
    );
}

export default ConfirmedReservations;