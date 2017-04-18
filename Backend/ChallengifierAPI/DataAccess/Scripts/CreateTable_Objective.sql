USE[Challengifier]
GO
CREATE TABLE [dbo].[Objective](
	[Objective_ID] [uniqueidentifier] NOT NULL,
	[Name] [nvarchar](50) NOT NULL,
	[Description] [nvarchar](250) NOT NULL,
	[Deadline] [datetime] NOT NULL,
	[Expected_Outcome] [nvarchar](150) NULL,
	[Challenge_ID] [uniqueidentifier] NULL,
    PRIMARY KEY ([Objective_ID]))
GO

GO
ALTER TABLE [Objective]
ADD FOREIGN KEY (Challenge_ID)
REFERENCES Challenge(Challenge_ID)