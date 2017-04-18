USE [Challengifier]
GO

CREATE TABLE [dbo].[Milestone](
	[Milestone_ID] [uniqueidentifier] NOT NULL,
	[Name] [nvarchar](50) NOT NULL,
	[Description] [nvarchar](50) NULL,
	[StartDate] [date] NOT NULL,
	[EndDate] [date] NOT NULL,
	[Objective_ID] [uniqueidentifier] NOT NULL,
    PRIMARY KEY ([Milestone_ID]))