//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated from a template.
//
//     Manual changes to this file may cause unexpected behavior in your application.
//     Manual changes to this file will be overwritten if the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

namespace DataAccess.Entities
{
    using System;
    using System.Collections.Generic;
    
    public partial class Milestone
    {
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2214:DoNotCallOverridableMethodsInConstructors")]
        public Milestone()
        {
            this.Picture = new HashSet<Picture>();
        }
    
        public System.Guid Milestone_ID { get; set; }
        public string Name { get; set; }
        public string Description { get; set; }
        public System.DateTime StartDate { get; set; }
        public System.DateTime EndDate { get; set; }
        public System.Guid Objective_ID { get; set; }
        public Nullable<System.Guid> PlanningStep_ID { get; set; }
    
        public virtual Objective Objective { get; set; }
        public virtual PlanningStep PlanningStep { get; set; }
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<Picture> Picture { get; set; }
    }
}
