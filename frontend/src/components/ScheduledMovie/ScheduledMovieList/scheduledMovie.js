import React, {useState} from "react";
import {Link} from 'react-router-dom';
import ReactPaginate from 'react-paginate';
import scheduledMovie from "../../Ticket/ConfirmedReservationsList/confirmedReservations";

const ScheduledMovie = (props) => {
    //console.log("SELECTED MOVIE" +props.movies(0).id.id)

    const [pageNumber, setPageNumber] = useState(0);
    const itemsPerPage = 6;
    const pageVisited = pageNumber * itemsPerPage;
    const pageCount = Math.ceil(props.scheduledMovies.length / itemsPerPage);

    const handlePageChange = ({selected}) => {
        setPageNumber(selected);
    }

    const displayScheduledMovies = props.scheduledMovies
        .slice(pageVisited, pageVisited + itemsPerPage)
        .map((term) => {
            return (
                // <div className={"row"}>
                //     <div className={"col"}>
                //         {term.startTime.hour}:
                //         {term.startTime.minutes.toString().length === 1 ? "0"+term.startTime.minutes.toString() : term.startTime.minutes.toString()} - {term.endTime.hour}:
                //         {term.endTime.minutes.toString().length === 1 ? "0"+term.endTime.minutes.toString() : term.endTime.minutes.toString()}
                //     </div>
                // </div>
                <tr>
                    <td>
                        {term.dateAndTimeScheduled.hour}:
                        {term.dateAndTimeScheduled.minutes.toString().length === 1 ? "0"+term.dateAndTimeScheduled.minutes.toString() : term.dateAndTimeScheduled.minutes.toString()}
                    </td>
                    <td>{props.movies.map((obj) => {
                        if(obj.id.id === term.movieId.id){
                            return (
                                <p>{obj.name}</p>
                            );
                        }
                    })}</td>
                    <td>{term.sales}</td>
                    <td>{term.ticketPrice.amount} {term.ticketPrice.currency}</td>
                    <td>{term.movieId.id}</td>
                    <td>{term.scheduledMovieStatus}</td>
                    <td>
                        <Link
                            onClick={() => props.onBookTickets(term.id.id)}
                            to={`/ticket/makeNewReservation/${term.id.id}`}>
                            Book tickets
                        </Link>
                    </td>
                    <td>
                        <Link
                            onClick={() => {
                                props.onCancelScheduledMove(term.id.id)
                            }}
                            to={`/scheduledMovies`}>
                            Cancel scheduled movie
                        </Link>
                    </td>
                    <td>
                        <Link
                            onClick={() => {
                                props.onRescheduleMovie(term.id.id)
                            }}
                            to={`/scheduledMovies/reschedule/${term.id.id}`}>
                            Reschedule movie
                        </Link>
                    </td>
                </tr>
            );
        });

    return (
        <div className={"container mm-4 mt-5"}>
            <div className={"row"}>
                <h3 className={"text-danger"}>
                    Movie Schedule:
                </h3>
                <hr/>
                <br/>
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
                        {displayScheduledMovies}
                        </tbody>
                    </table>
                </div>
            </div>


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
    );
}

export default ScheduledMovie;