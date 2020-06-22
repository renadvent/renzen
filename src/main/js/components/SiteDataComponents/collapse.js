import React, { useState } from "react";

function collapse(props) {
  const ref = "#section" + props.id;
  const refTag = "section" + props.id;

  //const [ref_state,set_ref_state] = useState

  return (
    <div class="accordion" id="accordionExample">
      <div class="card">
        <div class="card-header" id="headingOne">
          <h2 class="mb-0">
            <button
              class="btn btn-link btn-block text-left"
              type="button"
              data-toggle="collapse"
              data-target={ref}
              aria-expanded="true"
              aria-controls="collapseOne"
            >
              View Walkthrough
            </button>
          </h2>
        </div>

        <div
          id={refTag}
          class="collapse show"
          aria-labelledby="headingOne"
          data-parent="#accordionExample"
        >
          <div class="card-body">
            Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus
            terry richardson ad squid. 3 wolf moon officia aute, non cupidatat
            skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod.
            Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid
            single-origin coffee nulla assumenda shoreditch et. Nihil anim
            keffiyeh helvetica, craft beer labore wes anderson cred nesciunt
            sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings
            occaecat craft beer farm-to-table, raw denim aesthetic synth
            nesciunt you probably haven't heard of them accusamus labore
            sustainable VHS.
          </div>
        </div>
      </div>
    </div>
  );
}

export default collapse;

{
  /* //   return (
//     <div class="container">
//     <p></p>
//       <div class="panel-group">
//         <div class="panel panel-default">
//           <div class="panel-heading">
//             <h4 class="panel-title">
//               <a data-toggle="collapse" href={ref}>
//                 View Walkthrough
//               </a>
//             </h4>
//           </div>
//           <div id={refTag} class="panel-collapse collapse">
//             <div class="panel-body">This is the content of the study guide</div>
//             <div class="panel-footer">Copyright 2020</div>
//           </div>
//         </div>
//       </div>
//     </div>
//   );
// } */
}
