USE[Challengifier]
GO

CREATE TABLE [dbo].[Picture](
	[Picture_ID] [uniqueidentifier] NOT NULL,
	[Picture] [varbinary](max) NULL,
	[Challenge_ID] [uniqueidentifier] NULL,
	[Objective_ID] [uniqueidentifier] NULL,
	[Milestone_ID] [uniqueidentifier] NULL,
	PRIMARY KEY ([Picture_ID]))
	GO
	ALTER TABLE Picture
ADD FOREIGN KEY (Milestone_ID)
REFERENCES Milestone(Milestone_ID)
GO

ALTER TABLE Picture
ADD FOREIGN KEY (Objective_ID)
REFERENCES Objective(Objective_ID)

GO
ALTER TABLE Picture
ADD FOREIGN KEY (Challenge_ID)
REFERENCES Challenge(Challenge_ID)
