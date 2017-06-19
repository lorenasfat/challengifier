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
    
    public partial class PlanningStep
    {
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2214:DoNotCallOverridableMethodsInConstructors")]
        public PlanningStep()
        {
            this.Milestone = new HashSet<Milestone>();
        }
    
        public System.Guid PlanningStep_ID { get; set; }
        public string Name { get; set; }
        public string Description { get; set; }
        public System.Guid Challenge_ID { get; set; }
        public string Duration_TimeUnitId { get; set; }
        public Nullable<int> Duration_TimeNumber { get; set; }
    
        public virtual Challenge Challenge { get; set; }
        public virtual TimeUnits TimeUnits { get; set; }
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<Milestone> Milestone { get; set; }
    }
}
